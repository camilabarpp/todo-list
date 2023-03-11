import {TaskModel} from "../service/task-model";
import {Injectable} from "@angular/core";
import {ComponentStore} from '@ngrx/component-store';
import {Subscription, switchMap, tap} from "rxjs";
import {TaskService} from "../service/task.service";
import {TaskRequest} from "../service/task-request";

export interface TaskState {
  tasks: TaskModel[];
  selectedTaskId?: number;
}

const initialState: TaskState = {
  tasks: [],
  selectedTaskId: undefined,
}

@Injectable()
export class TaskStore extends ComponentStore<TaskState> {
  private _subs = new Subscription();

  constructor(
    private readonly _taskService: TaskService
  ) {
    super(initialState);
  }

  readonly tasks$ = this.select((state) => state.tasks);

  readonly selectedTask$ = this.select((state) => {
    const selectedTask = state.tasks?.find((task) => task.id === state.selectedTaskId);
    return selectedTask ?? null;
  });

  readonly loadTasks = this.effect<void>((trigger$) =>
    trigger$.pipe(
      switchMap(() => this._taskService.getTasks()),
      tap((tasks) => {
        this.patchState({tasks}),
          console.log(tasks)
      }),
    ),
  );

  // readonly getTaskById = this.effect<{ id: number}>((payload$) =>
  //   payload$.pipe(
  //     switchMap(({id}) => this._taskService.getTaskById(id)),
  //     tap((updatedTask) =>
  //       this.patchState((state) => ({
  //         tasks: state.tasks?.map((task) => (task.id === updatedTask.id ? updatedTask : task)),
  //       })),
  //     ),
  //   ),
  // );

  readonly getTaskById = this.effect<number>((id$) =>
    id$.pipe(
      switchMap((id) => this._taskService.getTaskById(id)),
      // tap((task) => this.patchState({ selectedTask: task })),
    )
  );


  readonly createTask = this.effect<TaskRequest>((task$) =>
    task$.pipe(
      switchMap((task) => this._taskService.createTask(task)),
      tap((createdTask) =>
        this.patchState((state) => ({
          tasks: [...state.tasks, createdTask],
        })),
      ),
    ),
  );

  readonly updateTask = this.effect<{ id: number; task: TaskRequest }>((payload$) =>
    payload$.pipe(
      switchMap(({id, task}) => this._taskService.updateTask(id, task)),
      tap((updatedTask) =>
        this.patchState((state) => ({
          tasks: state.tasks?.map((task) => (task.id === updatedTask.id ? updatedTask : task)),
        })),
      ),
    ),
  );

  readonly deleteTask = this.effect<number>((id$) =>
    id$.pipe(
      switchMap((id) => this._taskService.deleteTask(id)),
      tap((deletedId) =>
        this.patchState((state) => ({
          tasks: state.tasks ? state.tasks.filter((task) => task.id !== deletedId) : []
        })),
      ),
    ),
  );

  readonly deleteAllTasks = this.effect<void>((trigger$) =>
    trigger$.pipe(
      switchMap(() => this._taskService.deleteAllTasks()),
      tap(() => this.patchState({tasks: []})),
    ),
  );
}

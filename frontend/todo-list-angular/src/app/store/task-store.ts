import {TaskModel} from "../service/task-model";
import {Injectable} from "@angular/core";
import {ComponentStore} from '@ngrx/component-store';
import {Observable, Subscription, switchMap, tap} from "rxjs";
import {TaskService} from "../service/task.service";

export interface TaskState {
  tasks: TaskModel[];
  selectedTaskId?: number;
  taskDetails: any
}

const initialState: TaskState = {
  tasks: [],
  selectedTaskId: undefined,
  taskDetails: undefined
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
  readonly taskDetails$ = this.select((state) => state.taskDetails);

  readonly selectedTask$: Observable<TaskModel> = this.select(state => state.taskDetails);


  // readonly selectedTask$ = this.select((state) => {
  //   const selectedTask = state.tasks?.find((task) => task.id === state.selectedTaskId);
  //   return selectedTask ?? null;
  // });

  readonly loadTasks = this.effect<void>((trigger$) =>
    trigger$.pipe(
      switchMap(() => this._taskService.getTasks()),
      tap((tasks) => {
        this.patchState({tasks}),
          this.taskDetails$.subscribe(
            t => t = tasks
          )
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

  readonly getTaskById = this.effect<any>((id$: Observable<any>) =>
    id$.pipe(
      switchMap((id) => this._taskService.getTaskById(id)),
      tap((task) => {
        this.patchState({selectedTaskId: task.id});
        console.log(task);
      })
    )
  );

  readonly createTask = this.effect<any>((task$) =>
    task$.pipe(
      switchMap((task) => this._taskService.createTask(task)),
      tap((createdTask) =>
        this.patchState((state) => ({
          tasks: [...state.tasks, createdTask],
        })),
      ),
    ),
  );

  readonly updateTask = this.effect<{ id: number; task: TaskModel }>((payload$) =>
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

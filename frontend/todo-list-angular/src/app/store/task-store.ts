import {TaskModel} from "../service/task-model";
import {Injectable, OnDestroy} from "@angular/core";
import {ComponentStore} from '@ngrx/component-store';
import {Observable, Subject, Subscription, switchMap, tap, withLatestFrom} from "rxjs";
import {TaskService} from "../service/task.service";

export interface TaskState {
  task?: TaskModel[];
  editorId: number | undefined;
  editedTask: TaskModel | undefined;
}

const initialState: TaskState = {
  task: [],
  editorId: undefined,
  editedTask: undefined
}

@Injectable()
export class TaskStore extends ComponentStore<TaskState> implements OnDestroy {
  private _saveEditTask$ = new Subject<void>();
  private _subs = new Subscription();

  constructor(
    private readonly _taskService: TaskService
  ) {
    super(initialState);

    const saveWithData$ = this._saveEditTask$.pipe(
      withLatestFrom(this.editedTask$, this.editorId$),
      switchMap(([, task, taskId]) =>
        this._taskService.saveTask(taskId, task)
      )
    );

    this._subs.add(
      saveWithData$.subscribe({
        next: (task) => {
          this.updateTask(task);

          this.clearEditedTask();
        },
        error: (error) => {
          console.error('An error happened while saving:', error);
        }
      })
    );
  }

  readonly task$ = this.select(({task}) => task);
  readonly editorId$ = this.select(({editorId}) => editorId);
  readonly editedTask$ = this.select(({editedTask}) => editedTask).pipe(
    tap((task) => console.log('editedTask$', task)));

  readonly loadTask = this.updater((state, task: TaskModel[] | null) => ({
    ...state,
    task: task || [],
  }));

  readonly setEditorId = this.updater((state, editorId: number | undefined) => ({
    ...state,
    editorId,
  }));

  readonly setEditedTask = this.updater((state, editedTask: TaskModel | undefined) => ({
    ...state,
    editedTask,
  }));

  readonly editTask = this.effect((taskId$: Observable<number | undefined>) =>
    taskId$.pipe(
      withLatestFrom(this.task$),
      tap<[number | undefined, TaskModel[]]>(([taskId, task]) => {
        this.setEditorId(taskId);

        const taskToEdit =
          !taskId || taskId == 0 ? undefined : task.find((task) => task.id == taskId);

        this.setEditedTask(...taskToEdit);
      })
    ));

  readonly updateTask = this.effect((task$: Observable<TaskModel>) =>
    task$.pipe(
      withLatestFrom(this.task$),
      tap<[TaskModel, TaskModel[]]>(([task, tasks]) => {
        const id = task.id;
        const index = tasks.findIndex((cur) => {
          console.log('compare', cur, id, cur.id === id);
          return cur.id === id;
        });

        console.log('index', index, task, tasks);

        if (index > -1) {
          const editedPeople = [...tasks];
          editedPeople[index] = task;

          this.loadTask(editedPeople);
        }
      })
    )
  );

  ngOnDestroy() {
    this._subs.unsubscribe();
  }

  cancelEditTask() {
    this.clearEditedTask();
  }

  private clearEditedTask() {
    this.setEditorId(undefined);
    this.setEditedTask(undefined);
  }

  saveEditTask(value: TaskModel) {
    this._saveEditTask$.next(value);
  }
}

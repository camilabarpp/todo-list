import {TaskModel} from "../service/task-model";
import {Injectable} from "@angular/core";
import {ComponentStore} from '@ngrx/component-store';
import {Subscription, switchMap, tap} from "rxjs";
import {TaskService} from "../service/task.service";

export interface TaskState {
  task?: TaskModel[];
  selectedTaskId?: number;
}

const initialState: TaskState = {
  task : [],
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

  readonly task$ = this.select((state) => state.task);

  // readonly taskTitle$ = this.select((state) => state.task?.title);
  //
  // readonly taskCompleted$ = this.select((state) => state.task?.completed);

  readonly loadTask = this.effect<void>((trigger$) =>
    trigger$.pipe(
      switchMap(() => this._taskService.getTasks()),
      tap((task) => this.patchState({task})),
    ),
  );

  readonly clearTask = this.effect<void>((trigger$) =>
    trigger$.pipe(
      tap(() => this.patchState({task: undefined})),
    ),
  );
}

import {Component, ElementRef, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {TaskStore} from "../../store/task-store";
import {TaskModel} from "../../service/task-model";
import {ActivatedRoute, Router} from "@angular/router";
import {logMessages} from "@angular-devkit/build-angular/src/builders/browser-esbuild/esbuild";

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list-body.component.html',
  styleUrls: ['./todo-list-body.component.scss']
})
export class TodoListBodyComponent {

  savingTask: boolean = false;
  tasks$ = this.taskStore.tasks$;
  @Input() tasks: TaskModel[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() remove = new EventEmitter(false);
  @Output() selected = new EventEmitter(false);

  constructor(
    private taskStore: TaskStore,
    private router: Router,
    private route: ActivatedRoute,
  ) {
  }

  onAdd() {
    this.add.emit(true);
  }

  onEdit(taskId: number) {
    this.router.navigate(['edit', taskId], {relativeTo: this.route}).then(r => console.log(r));
  }

  onDelete(taskModel: TaskModel) {
    this.remove.emit(taskModel);
  }

  onSelected(taskModel: TaskModel) {
    this.selected.emit(taskModel);
    this.taskStore.loadTasks();
  }
}

import { Component } from '@angular/core';
import {TaskStore} from "../store/task-store";

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.scss']
})
export class TodoListComponent {
  tasks$ = this.taskStore.tasks$;

  constructor(private taskStore: TaskStore) {
    this.taskStore.loadTasks();

    // this.tasks$.subscribe(
    //   a => console.log(a)
    // )
  }
}

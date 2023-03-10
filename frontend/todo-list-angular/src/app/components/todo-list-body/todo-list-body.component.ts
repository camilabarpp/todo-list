import {Component} from '@angular/core';
import {TaskStore} from "../../store/task-store";

@Component({
  selector: 'app-todo-list-body',
  templateUrl: './todo-list-body.component.html',
  styleUrls: ['./todo-list-body.component.scss']
})
export class TodoListBodyComponent {
  tasks$ = this.taskStore.tasks$;

  constructor(private taskStore: TaskStore) {
    this.taskStore.loadTasks();

    this.tasks$.subscribe(
      a => console.log(a[0])
    )
  }
}

import {Component} from '@angular/core';
import {TaskStore} from "./store/task-store";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  tasks$ = this.taskStore.tasks$;

  constructor(private taskStore: TaskStore) {
    this.taskStore.loadTasks();

    this.tasks$.subscribe(
      a => console.log(a)
    )
  }
}

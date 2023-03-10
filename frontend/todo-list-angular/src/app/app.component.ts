import {Component} from '@angular/core';
import {TaskStore} from "./store/task-store";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  tasks$ = this.taskStore.task$;

  constructor(private taskStore: TaskStore) {
    this.taskStore.loadTask();

    this.tasks$.subscribe(
      a => console.log(a)
    )
  }
}

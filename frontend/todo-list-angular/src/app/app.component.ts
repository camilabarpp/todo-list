import {Component} from '@angular/core';
import {TaskService} from "./service/task.service";
import {catchError, Observable, of} from "rxjs";
import {TaskModel} from "./service/task-model";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {


  tasks$: Observable<TaskModel[]> | null = null;

  constructor(private taskService: TaskService) {
    this.refresh();
  }

  refresh() {
    this.tasks$ = this.taskService.list()
      .pipe(
        catchError(error => {
          // this.onError('Erro ao carregar cursos.');
          return of([])
        })
      );
    console.log(this.tasks$.subscribe((data) => console.log(data)));
  }
}

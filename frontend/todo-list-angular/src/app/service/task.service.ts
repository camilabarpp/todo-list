import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {delay, map, Observable, of} from "rxjs";
import {TaskModel} from "./task-model";
import {ApiResponse} from "./api-response";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private readonly API = 'api/v1/tasks';

  constructor(private httpClient: HttpClient) {
  }

  getTasks(): Observable<TaskModel[]> {
    return this.httpClient
      .get(this.API)
      .pipe(
        map((res: ApiResponse) =>
          res.results.map((person, index) => ({...person, id: index}))
        )
      );
  }

  saveTask(id: number, person: TaskModel) {
    return of(person).pipe(delay(Math.random() * 2000));
  }
}

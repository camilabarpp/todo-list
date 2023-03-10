import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {first} from "rxjs";
import {TaskModel} from "./task-model";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private readonly API = 'api/v1/tasks';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<TaskModel[]>(this.API)
      .pipe(
        first(),
        //delay(5000),
        // tap(courses => console.log(courses))
      );
  }
}

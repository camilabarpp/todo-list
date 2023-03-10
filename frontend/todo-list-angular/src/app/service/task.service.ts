import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TaskModel} from "./task-model";
import {TaskRequest} from "./task-request";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private readonly API = 'api/v1/tasks';

  constructor(private httpClient: HttpClient) {
  }

  getTasks(): Observable<TaskModel[]> {
    return this.httpClient.get<TaskModel[]>(this.API);
  }

  getTaskById(id: number): Observable<TaskModel> {
    return this.httpClient.get<TaskModel>(`${this.API}/${id}`);
  }

  createTask(task: TaskRequest): Observable<TaskModel> {
    return this.httpClient.post<TaskModel>(this.API, task);
  }

  updateTask(id: number, task: TaskRequest): Observable<TaskModel> {
    return this.httpClient.put<TaskModel>(`${this.API}/${id}`, task);
  }

  deleteTask(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.API}/${id}`);
  }

  deleteAllTasks(): Observable<void> {
    return this.httpClient.delete<void>(`${this.API}`);
  }
}

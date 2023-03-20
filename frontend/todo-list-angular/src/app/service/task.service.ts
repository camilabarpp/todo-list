import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {first, Observable} from "rxjs";
import {TaskModel} from "./task-model";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private readonly API = 'api/v2/tasks';

  constructor(private httpClient: HttpClient) {
  }

  getTasks(): Observable<TaskModel[]> {
    return this.httpClient.get<TaskModel[]>(this.API);
  }

  getTaskById(id: number): Observable<TaskModel> {
    return this.httpClient.get<TaskModel>(`${this.API}/${id}`);
  }

  createTask(record: any) {
    return this.httpClient.post<TaskModel>(this.API, record).pipe(first());
  }

  updateTask(id: number, task: TaskModel): Observable<TaskModel> {
    return this.httpClient.put<TaskModel>(`${this.API}/${id}`, task);
  }

  updateTaskStatus(id: number, taskStatus: { completed: any }): Observable<TaskModel> {
    return this.httpClient.patch<TaskModel>(`${this.API}/${id}`, taskStatus);
  }

  deleteTask(id: number): Observable<number> {
    return this.httpClient.delete<number>(`${this.API}/${id}`);
  }

  deleteAllTasks(): Observable<void> {
    return this.httpClient.delete<void>(`${this.API}`);
  }
}

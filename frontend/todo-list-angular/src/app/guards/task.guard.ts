import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Resolve, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable, of} from 'rxjs';
import {TaskModel} from "../service/task-model";
import {TaskService} from "../service/task.service";

@Injectable({
  providedIn: 'root'
})
export class TaskResolver implements Resolve<TaskModel> {

  constructor(private service: TaskService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TaskModel> {
    if (route.params && route.params['id']) {
      return this.service.getTaskById(route.params['id']);
    }
    return of({
      id: 0,
      name: '',
      description: '',
      completed: false,
      weekDay: ''
    });
  }
}

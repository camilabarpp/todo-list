import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {TodoListComponent} from "./todo-list.component";
import {TodoFormComponent} from "./todo-form/todo-form.component";
import {TaskResolver} from "../guards/task.guard";

const routes: Routes = [
  {
    path: '',
    component: TodoListComponent
  },
  {
    path: 'new',
    component: TodoFormComponent,
    resolve: {
      course: TaskResolver
    }
  },
  {
    path: 'edit/:id',
    component: TodoFormComponent,
    resolve: {
      course: TaskResolver
    }
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  declarations: []
})
export class TodoListRoutingModule {
}

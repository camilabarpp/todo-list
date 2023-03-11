import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TodoListComponent} from './todo-list.component';
import {SharedModule} from "../shared/shared.module";
import {MaterialModule} from "../material/material.module";
import {TodoListRoutingModule} from "./todo-list-routing.module";
import {TodoListBodyComponent} from "./todo-list/todo-list-body.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { TodoFormComponent } from './todo-form/todo-form.component';

@NgModule({
  declarations: [
    TodoListComponent,
    TodoListBodyComponent,
    TodoFormComponent,
  ],
    imports: [
        CommonModule,
        SharedModule,
        MaterialModule,
        TodoListRoutingModule,
        ReactiveFormsModule,
        FormsModule
    ]
})
export class TodoListModule {
}

import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TodoListComponent} from './todo-list.component';
import {SharedModule} from "../shared/shared.module";
import {MaterialModule} from "../material/material.module";
import {TodoListRoutingModule} from "./todo-list-routing.module";
import {TodoListBodyComponent} from "./todo-list-body/todo-list-body.component";
import { TodoAddItensComponent } from './todo-add-itens/todo-add-itens.component';
import { TodoButtonDeleteComponent } from './todo-button-delete/todo-button-delete.component';

@NgModule({
  declarations: [
    TodoListComponent,
    TodoListBodyComponent,
    TodoAddItensComponent,
    TodoButtonDeleteComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    MaterialModule,
    TodoListRoutingModule
  ]
})
export class TodoListModule {
}

import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'task' },
  {
    path: 'task',
    loadChildren: () => import('./components-v2/todo-list.module').then(m => m.TodoListModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

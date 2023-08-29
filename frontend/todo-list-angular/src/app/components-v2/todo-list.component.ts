import {Component} from '@angular/core';
import {TaskStore} from "../store/task-store";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute, Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {TaskModel} from "../service/task-model";
import {ErrorDialogComponent} from "../shared/error-dialog/error-dialog.component";
import {ConfirmationDialogComponent} from "../shared/confirmation-dialog/confirmation-dialog.component";

@Component({
  selector: 'todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.scss']
})
export class TodoListComponent {
  tasks$ = this.taskStore.tasks$;

  id!: number;
  completed?: boolean = false;

  constructor(
    private taskStore: TaskStore,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.refresh();
  }

  refresh() {
    this.taskStore.loadTasks();
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  onSelected(selected : TaskModel) {
    this.taskStore.updateTaskStatus({ id: selected.taskId, completed: !selected.completed });
    this.refresh();
  }

  onAdd() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  onEdit(taskId: number) {
    this.router.navigate(['edit', taskId], {relativeTo: this.route});
  }

  onRemove(course: TaskModel) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja remover esse curso?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.taskStore.deleteTask(course.taskId);
        this.snackBar.open('Curso removido com sucesso!', 'X', {
          duration: 5000,
          verticalPosition: 'bottom',
          horizontalPosition: 'center'
        });
        this.refresh();
      }
    });
  }
}

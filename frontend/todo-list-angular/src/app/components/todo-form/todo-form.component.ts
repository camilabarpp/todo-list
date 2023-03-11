import {Component, OnInit} from '@angular/core';
import {NonNullableFormBuilder, Validators} from "@angular/forms";
import {TaskStore} from "../../store/task-store";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {TaskModel} from "../../service/task-model";
import {TaskRequest} from "../../service/task-request";

@Component({
  selector: 'app-todo-form',
  templateUrl: './todo-form.component.html',
  styleUrls: ['./todo-form.component.scss']
})
export class TodoFormComponent implements OnInit {

  tasks$ = this.taskStore.tasks$;

  taskId?: number;

  taskForm = this.fb.group({
    id: [0],
    name: ['', [Validators.required]],
    description: [''],
    completed: [false],
    weekDay: ['', [Validators.required]]
  });


  constructor(
    private taskStore: TaskStore,
    private snackBar: MatSnackBar,
    private router: Router,
    private fb: NonNullableFormBuilder,
    private route: ActivatedRoute,
  ) {
    this.taskStore.loadTasks();
  }

  // ngOnInit() {
  //   this.taskStore.tasks$.subscribe(
  //     task => {
  //       this.taskForm.setValue({
  //         name: task[0].name,
  //         completed: task[0].completed,
  //         description: task[0].description,
  //         id: task[0].id,
  //         weekDay: task[0].weekDay
  //       })
  //     }
  //   )
  // }

  ngOnInit() {
    this.taskId = this.route.snapshot.params['id'];

    console.log(this.taskId)
  }


  onSubmit() {
    if (this.taskForm.valid) {
      const task: { weekDay: string; name: string; description: string | undefined; completed: boolean } = {
        name: this.taskForm.get('name')!.value,
        description: this.taskForm.get('description')?.value,
        completed: false,
        weekDay: this.taskForm.get('weekDay')!.value,
      };

      this.taskStore.createTask(task);
      this.onSuccess();
      this.onCancel();
    }
  }

  getErrorMessage(fieldName
                    :
                    string
  ) {
    const field = this.taskForm.get(fieldName);

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }
    return 'Campo Inválido';
  }

  private onSuccess() {
    this.snackBar.open('Tarefa salva com sucesso!', '', {duration: 5000});
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Erro ao salvar tarefa.', '', {duration: 5000});
  }

  onCancel() {
    this.router.navigate(['../../'], {relativeTo: this.route});
  }
}

import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {TaskStore} from "../../store/task-store";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {TaskService} from "../../service/task.service";

@Component({
  selector: 'app-todo-form',
  templateUrl: './todo-form.component.html',
  styleUrls: ['./todo-form.component.scss']
})
export class TodoFormComponent implements OnInit {

  tasks$ = this.taskStore.tasks$;

  constructor(
    private taskStore: TaskStore,
    private service: TaskService,
    private snackBar: MatSnackBar,
    private router: Router,
    private fb: FormBuilder,
    private route: ActivatedRoute,
  ) {
    this.taskStore.loadTasks();
  }

  form = this.fb.group({
    id: 0,
    name: ['', [Validators.required,
      Validators.minLength(5),
      Validators.maxLength(100)]],
    description: [''],
    weekDay: ['', [Validators.required]],
  });

  ngOnInit(): void {
    const id: number = this.route.snapshot.params['id'];
    if(id){
      this.service.getTaskById(id).subscribe(
        taskDetail => {
          console.log(taskDetail)
          this.form.setValue({
              id: taskDetail.id,
              name: taskDetail.name,
              description: taskDetail.description,
              weekDay: taskDetail.weekDay
            }
          );
        },
      )
    }

  }


  onSubmit() {
    this.service.createTask(this.form.value)
      .subscribe(result => this.onSuccess(), error => this.onError());
  }

  getErrorMessage(fieldName: string) {
    const field = this.form?.get(fieldName);

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

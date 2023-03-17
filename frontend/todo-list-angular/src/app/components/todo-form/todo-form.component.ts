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
  taskId? : number;

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
      Validators.minLength(3),
      Validators.maxLength(15)]],
    description: [''],
    weekDay: ['', [Validators.required]],
  });

  ngOnInit(): void {
    const task = this.route.snapshot.data['task'];
    console.log(task)
    const id: number = this.route.snapshot.params['id'];
    if (id) {
      this.service.getTaskById(id).subscribe(
        taskDetail => {
          this.taskStore.updateTask({id: id, task: taskDetail});
          this.taskId = taskDetail.id;
          console.log(this.taskId)
          this.form.setValue({
              id: taskDetail.id,
              name: taskDetail.name,
              description: taskDetail.description,
              weekDay: taskDetail.weekDay
            },
          );
        },
      )
    }

  }


  onSubmit() {
    if (this.form.valid) {
      const task: any = {
        id: this.form.get('id')!.value,
        name: this.form.get('name')!.value,
        description: this.form.get('description')?.value,
        completed: false, // Adicionado aqui
        weekDay: this.form.get('weekDay')!.value,
      }

      if (this.form.value.id) {
        this.taskStore.updateTask({task: task, id: task.id});
        console.log(task.name)
        console.log(task.id)
      } else {
        this.taskStore.createTask(task);
        console.log('criando')
      }
      this.onSuccess();
      this.onCancel();
    } else {
      this.onError();
    }
  }

  getErrorMessage(fieldName: string) {
    const field = this.form?.get(fieldName);

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }

    if (field?.hasError('minlength')) {
      const requiredLength: number = field?.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Tamanho mínimo precisa ser de ${requiredLength} caracteres.`;
    }

    if (field?.hasError('maxlength')) {
      const requiredLength: number = field?.errors ? field.errors['maxlength']['requiredLength'] : 15;
      return `Maior que ${requiredLength} caracteres.`;
    }
    return 'Campo obrigatório';
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

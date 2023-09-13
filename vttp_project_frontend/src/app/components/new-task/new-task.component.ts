import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, ValidationErrors, Validators } from '@angular/forms';
import { TaskDetails, Tasks } from 'src/app/model/tasks';
import { TaskService } from 'src/app/service/task.service';

@Component({
  selector: 'app-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent {

  tasks = this.taskService.getTasks();


  constructor(private taskService: TaskService, private formBuilder: FormBuilder) {}

  taskForm = this.formBuilder.group({
    description: ['', [Validators.required, Validators.minLength(5)]],
    priority: ['low', [Validators.required]],
    due: ['', [Validators.required, this.duePastDateValidator]]
  })

  onSubmit() {
    this.taskService.addTask(this.taskForm.value as TaskDetails)
  }

  duePastDateValidator(control: AbstractControl) {
    const date = Date.parse(control.value);
    const dateNow = new Date().setUTCHours(0, 0, 0, 0);
    return date < dateNow ? {duePastDate: {value: control.value}} as ValidationErrors : null;
  }

  get content() { return this.taskForm.get('content'); }
  get priority() { return this.taskForm.get('priority'); }
  get category() { return this.taskForm.get('category')}
  get due() { return this.taskForm.get('dueDate'); }


}

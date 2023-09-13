import { Component } from '@angular/core';
import { TaskService } from 'src/app/service/task.service';

@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.css']
})
export class TaskDetailsComponent {

  constructor(private taskSvc: TaskService) {
  }

  getTasks(showCompletedOnly = false) {
    return this.taskSvc.getTasks(showCompletedOnly);
  }

  completeTask(id: number) {
    this.taskSvc.completeTask(id);
  }

  incompleteTask(id: number) {
    this.taskSvc.incompleteTask(id);
  }

  removeTask(id: number) {
    this.taskSvc.removeTask(id);
  }
}

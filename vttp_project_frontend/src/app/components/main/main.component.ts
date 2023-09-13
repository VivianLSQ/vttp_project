import { TaskService } from 'src/app/service/task.service';
import { Component, OnInit } from '@angular/core';
import { Tasks } from 'src/app/model/tasks';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  tasks: Tasks[] = [];
  constructor(private taskSvc: TaskService){

  }

  ngOnInit() {
    this.taskSvc.findAll().subscribe(data => {
      this.tasks = data
    })
  }

}

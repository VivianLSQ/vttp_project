import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, firstValueFrom } from 'rxjs';
import { Tasks } from '../model/tasks';
import { TaskDetails } from './../model/tasks';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http : HttpClient) { }

  getTask():Promise<Tasks>{
    return firstValueFrom(this.http.get<Tasks>("/api/task"))
  }

  findAll():Observable<Tasks[]>{
    return this.http.get<Tasks[]>("/api/task")
  }

  save(task: Tasks){
    return this.http.post<Tasks>("/api/task", task);
  }


  taskD: TaskDetails[] = [];
  id: number = 1;
  weight = (t: TaskDetails): number => this.weights[t.priority];
  weights: {[index: string]: number} = {
    high: 3,
    medium: 2,
    low: 1
  };

  getTasks(showCompletedOnly = false): TaskDetails[] {
    const taskD = this.taskD
      .sort((a, b) => {
        return this.weight(b) - this.weight(a);
      });

    if (showCompletedOnly) {
      return taskD.filter(task => task.isCompleted);
    }
    return taskD.filter(task => !task.isCompleted);
  }

  addTask(taskD: TaskDetails) {
    this.taskD.push({...taskD, id: this.id++, isCompleted: false});
  }

  completeTask(id: number) {
    this.taskD[this.taskD.findIndex(task => task.id == id)].isCompleted = true;
  }

  incompleteTask(id: number) {
    this.taskD[this.taskD.findIndex(task => task.id == id)].isCompleted = false;
  }

  removeTask(id: number) {
    this.taskD.splice(this.taskD.findIndex(task => task.id == id), 1);
  }

  editTask(id: number, taskD: TaskDetails) {
    this.taskD[this.taskD.findIndex(task => task.id == id)] = taskD;
  }
}

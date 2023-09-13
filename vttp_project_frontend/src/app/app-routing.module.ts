import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { NewTaskComponent } from './components/new-task/new-task.component';
import { TaskDetailsComponent } from './components/task-details/task-details.component';

const routes: Routes = [
  {path: "", component: MainComponent, title:"Home Page"},
  {path: "/task", component: NewTaskComponent, title:"Add New Task"},
  {path: "/taskDetails", component: TaskDetailsComponent, title:"Task Details"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { NewTaskComponent } from './components/new-task/new-task.component';
import { TaskDetailsComponent } from './components/task-details/task-details.component';
import { EmailFormComponent } from './components/email-form/email-form.component';
import { GeoLocationComponent } from './components/geo-location/geo-location.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { UserRegistrationComponent } from './components/user-registration/user-registration.component';

const routes: Routes = [
  {path: "", component: MainComponent, title:"Home Page"},
  {path: "/task", component: NewTaskComponent, title:"Add New Task"},
  {path: "/taskDetails", component: TaskDetailsComponent, title:"Task Details"},
  {path: "/task/:taskId/update", component: TaskDetailsComponent, title:"Task Details"}, //to edit
  {path: "/emailForm", component: EmailFormComponent, title:"Email Confirmation"},
  {path: "/geoLocation", component: GeoLocationComponent, title:"Geofencing Page"},
  {path: "/authentication/login", component: UserLoginComponent, title:"Login Page"},
  {path: "/authentication/register", component: UserRegistrationComponent, title:"Registration Page"},
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }

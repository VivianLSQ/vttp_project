import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MainComponent } from './components/main/main.component';
import { NewTaskComponent } from './components/new-task/new-task.component';
import { TaskDetailsComponent } from './components/task-details/task-details.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { TaskService } from './service/task.service';
import { MatDialogModule } from '@angular/material/dialog';
import { PushNotificationsService } from './service/push-notifications.service';
import { WebsocketChatComponent } from './components/websocket-chat/websocket-chat.component';
import { FormsModule } from '@angular/forms'; //to handle two way binding - ngModel
import { AngularFireModule } from '@angular/fire/compat';
import { environment } from "../environments/environment";
import { EmailFormComponent } from './components/email-form/email-form.component';
import { EmailService } from './service/email.service';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { AlertComponent } from './components/alert/alert.component';
import { UserRegistrationComponent } from './components/user-registration/user-registration.component';
import { GeoLocationComponent } from './components/geo-location/geo-location.component';
import { initializeApp } from "firebase/app/";
import { getMessaging } from "firebase/messaging";
import { AngularFireDatabaseModule } from '@angular/fire/compat/database';
import { AngularFireAuthModule } from "@angular/fire/compat/auth";
import { ServiceWorkerModule } from '@angular/service-worker';
import { UserLogoutComponent } from './user-logout/user-logout.component';




@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    NewTaskComponent,
    TaskDetailsComponent,
    WebsocketChatComponent,
    EmailFormComponent,
    UserLoginComponent,
    AlertComponent,
    UserRegistrationComponent,
    GeoLocationComponent,
    UserLogoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatDialogModule,
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: !isDevMode(),
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    }),
    //AngularFireModule.initializeApp(environment.firebase) //error here
  ],
  providers: [TaskService, PushNotificationsService, EmailService],
  bootstrap: [AppComponent]
})
export class AppModule {

  /*
   const firebaseConfig = {
    // ...
  };

  // Initialize Firebase
  const app = initializeApp(firebaseConfig);

  // Initialize Firebase Cloud Messaging and get a reference to the service
  const messaging = getMessaging(app);
  */

}

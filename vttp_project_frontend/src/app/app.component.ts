import { Component, OnInit } from '@angular/core';
import { environment } from "../environments/environment";
import { getMessaging, getToken, onMessage } from "firebase/messaging";
import { AlertService } from './service/alert.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'vttp_project_frontend';
  message:any = null;
  constructor(private alertSvc: AlertService) {}

  getSuccessMessage(){
    this.alertSvc.success("Yeah! Task succesfully added");
  }

  getWarningMessage(){
    this.alertSvc.warning("Oops! Please check data entry");
  }

  getErrorMessage() {
    this.alertSvc.error("Something went wrong, please try again");
  }

  getInfoMessage() {
    this.alertSvc.info("Important Upcoming Task!");
  }

  clearMessage() {
    this.alertSvc.clearAlertMessage();
  }


  ngOnInit(): void {
    this.requestPermission();
    this.listen();
  }

  requestPermission() {
    const messaging = getMessaging();
    getToken(messaging,
     { vapidKey: environment.firebase.vapidKey}).then( //error here
       (currentToken) => {
         if (currentToken) {
           console.log("Hurray! we got the token...");
           console.log(currentToken);
         } else {
           console.log('No registration token available. Request permission to generate token.');
         }
     }).catch((err) => {
        console.log('An error occurred while retrieving token.', err);
    });
  }

  listen() {
    const messaging = getMessaging();
    onMessage(messaging, (payload) => {
      console.log('Message received. ', payload);
      this.message=payload;
    });
  }
}

import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { AlertService } from 'src/app/service/alert.service';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnDestroy {

  private subscription$: Subscription;
  message: any;
  private timer!: Observable<any>;

  constructor(private alertSvc: AlertService){
    //Subscribe to alert messages
    this.subscription$ = alertSvc.getMessage().subscribe(message => {
      this.message = message;
    });
  }
  ngOnDestroy(): void{
    //unsubscribe on destroy to prevent memory leaks
    this.subscription$.unsubscribe();
  }
  closeMessage(){
    this.alertSvc.clearAlertMessage();
  }

}

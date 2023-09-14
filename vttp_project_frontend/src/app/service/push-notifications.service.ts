import { Injectable } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs';
//import * as StompJs from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class PushNotificationsService {
  //use WebSocket communication to remind user when approaching destination,
  //or upcoming task by sending push notifications

  private client: Client;

  constructor() {
    const socket = new WebSocket('/notifications');
    this.client = new Client({ webSocketFactory: () => socket });

      this.client.onConnect = (frame) => {
          console.log('Connected: ' + frame);
      };

      this.client.onDisconnect = (frame) => {
        console.log('Disconnected: ' + frame);
          // to display disconnected message to user
         //To-do: update frontend
      };

      //auto-reconnect
      this.client.configure({
        reconnectDelay: 3000, // Try reconnecting after 3 seconds
      });

        this.client.activate();

    };

  private subscribeToNotifications(): void {
    const destination = '/user/notifications';
    this.client.subscribe(destination, (message: IMessage) => {
      this.handleNotification(message.body);
    });
  }

  private handleNotification(notificationText: string): void {
    console.log('Received notification:', notificationText);
    //const notificationText = 'Upcoming dentist appointment at 3:00 PM.';
  }

  sendNotificationToUser(user: string, notification: string): void {
    const destination = `/user/notifications`;
    const publishParams = {
      destination,
      body: JSON.stringify({ text: notification }),
    };
    this.client.publish(publishParams);
  }

}




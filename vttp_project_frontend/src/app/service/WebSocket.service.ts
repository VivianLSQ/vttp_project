import { Injectable } from '@angular/core';
import { Client, Message } from '@stomp/stompjs';
//import * as StompJs from '@stomp/stompjs';
import SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  //used for reminding user about approaching destination, or upcoming task by sending push notifications

  private client: Client;

  constructor() {
    const socket = new SockJS('/chat');
    this.client = new Client({ webSocketFactory: () => socket });

      this.client.onConnect = (frame) => {
          console.log('Connected: ' + frame);
          // Handle connection logic here
      };

      this.client.onDisconnect = (frame) => {
          console.log('Disconnected: ' + frame);
          // Handle disconnection logic here
      };

      this.client.activate();
  }

  sendMessage(destination: string, message: string): void {
      this.client.publish({ destination, body: message });
  }

  // Implement methods for subscribing to and handling messages

  //The stompClient.subscribe() function takes a callback method which is called whenever a message arrives on the subscribed topic.
  //The connect() function makes use of the SockJS and stomp client to establish connection to the to the /websocketApp endpoint that we configured in Spring Boot application.
  //The client subscribes to "/topic/userFeedback" destination.
}

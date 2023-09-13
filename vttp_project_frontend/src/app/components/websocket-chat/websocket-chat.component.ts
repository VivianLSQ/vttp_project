import { Component, OnInit, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-websocket-chat',
  templateUrl: './websocket-chat.component.html',
  styleUrls: ['./websocket-chat.component.css']
})
export class WebsocketChatComponent {

  private http = inject (HttpClient)

  username: string = '';
  messageText: string = '';
  chatStarted: boolean = false;

  startChat() {
    if (this.username.trim() !== '') {
      this.chatStarted = true;
    }
  }


  sendMessage() {
    // Implement the logic to send messages (e.g., using a WebSocket or HTTP request)
    // You can use the this.messageText and this.username values here.

    if (this.messageText.trim() !== '') {
      // Define the message object to send to the server (see WebSocket Listener)
      const message = {
        username: this.username,
        text: this.messageText
      };

      // Replace 'YOUR_API_ENDPOINT' with your actual API endpoint
      const apiEndpoint = 'YOUR_API_ENDPOINT';
                      // "/topic/userFeedback" or "topic/public"

      // Send the message to the server using a POST request

      this.http.post(apiEndpoint, message).subscribe(() => {
          // Message sent successfully, you can update your UI here if needed
          console.log('Message sent successfully');
          this.messageText = ''; // Clear the message input field
        },
        (error) => {
          // Handle error if the message could not be sent
          console.error('Error sending message:', error);
        }
      );
    }
  }

   // this.ngOnInit(){
      //   const http$ = this.http.post(apiEndpoint, message);

      //   http$.subscribe(
      //     res => console.log('HTTP response', res),
      //     err => console.log('HTTP Error', err),
      //     () => console.log('HTTP request completed.')
      //   );

      // }

}



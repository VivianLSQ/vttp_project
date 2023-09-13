import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  constructor(private http : HttpClient) { }
  private readonly apiURL = '/api/confirmationMail';

  SendEmail(input: any) {
    return this.http.post(this.apiURL, input).pipe(
      map(
        (response: any) => {
          if (response) {
            return response;
          }
        },
        (error: any) => {
          if (error) {
            return error;
          }
        }
      )
    );
  }

}

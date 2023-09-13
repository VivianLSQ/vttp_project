import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  constructor(private http : HttpClient) { }
  private url = 'YOUR_API_ENDPOINT';

  SendEmail(input: any) {
    return this.http.post(this.url, input).pipe(
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

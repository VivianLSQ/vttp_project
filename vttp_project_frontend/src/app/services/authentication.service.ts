import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient ) { }

  private readonly apiUrl = '/api/auth';

  login(user: User): Observable<User> {
    const authData = btoa(`${user.email}:${user.password}`);
    const headers = new HttpHeaders().set('Authorization', `Basic ${authData}`);
    return this.http.post<User>(`${this.apiUrl}/login`, null, { headers });
  }

  logout(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/logout`);
  }

  register(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/register`, user);
  }

}

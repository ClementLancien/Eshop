import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../domain/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  authenticate(username: string, password: string): Observable<User> {
    return this.http.post<User>('/api/users/login', { username, password });
  }

  register(username: string, password:string, firstName: string, lastName: string): Observable<void> {
    return this.http.put<void>('/api/users/register', {username, password, firstName, lastName});
  }

}

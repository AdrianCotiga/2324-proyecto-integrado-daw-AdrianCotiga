import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Usuario } from '../../interfaces/user.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  [x: string]: any;

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/admin/users';

  getAllUsuarios(): Observable<Usuario[]> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('jwt')}`
    });
    return this.http.get<Usuario[]>(this.apiUrl, { headers });
  }
}

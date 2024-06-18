import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Perfil } from '../../interfaces/profile.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ProfileService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/player';

  getUsuarioPerfil(): Observable<Perfil> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem('jwt')}`
    });
    return this.http.get<Perfil>(`${this.apiUrl}/profile`, { headers });
  }
}
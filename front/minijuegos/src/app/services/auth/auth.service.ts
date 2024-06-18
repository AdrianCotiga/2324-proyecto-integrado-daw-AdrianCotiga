import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Usuario } from '../../interfaces/user.interface';
import { AuthResponse } from '../../interfaces/authResponse.interface';

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/auth';

  login(usuario: Usuario): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, usuario).pipe(
      tap((response: AuthResponse) => {
        localStorage.setItem('jwt', response.jwt);
      }),
      catchError(this.handleError)
    );
  }

  registrar(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.apiUrl}/signup`, usuario).pipe(
      catchError(this.handleError)
    );
  }

  logout(): void {
    localStorage.removeItem('jwt');
  }

  getToken(): string | null {
    return localStorage.getItem('jwt');
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'Unknown error!';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `Error ${error.status}:\n ${error.error}`;
    }
    return throwError(() => new Error(errorMessage));
  }
}

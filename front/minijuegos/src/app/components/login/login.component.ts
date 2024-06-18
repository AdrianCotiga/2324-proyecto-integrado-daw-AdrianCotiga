import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [ReactiveFormsModule, CommonModule, RouterModule]
})

export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string | null = null;

  constructor(private authService: AuthService, private fb: FormBuilder, private router: Router) {
    this.loginForm = this.fb.group({
      nombre: ['', Validators.required],
      contra: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
        next: (response) => {
          this.errorMessage = null;
          localStorage.setItem('jwt', response.jwt);
          this.router.navigate([response.redirectUrl]);
        },
        error: (error) => {
          this.errorMessage = error.message;
        }
      });
    }
  }
}
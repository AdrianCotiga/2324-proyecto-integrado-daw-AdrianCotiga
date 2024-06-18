import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  imports: [ReactiveFormsModule, CommonModule, RouterModule]
})

export class RegisterComponent {
  registerForm: FormGroup;
  errorMessage: string | null = null;

  constructor(private authService: AuthService, private fb: FormBuilder, private router: Router) {
    this.registerForm = this.fb.group({
      nombre: ['', Validators.required],
      contra: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      this.authService.registrar(this.registerForm.value).subscribe({
        next: (response: any) => {
          this.errorMessage = null;
          this.router.navigate(['/login']);
        },
        error: (error: any) => {
          this.errorMessage = error.message;
        }
      });
    }
  }
}
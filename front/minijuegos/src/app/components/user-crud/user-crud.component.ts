import { Component, OnInit, inject } from '@angular/core';
import { Usuario } from '../../interfaces/user.interface';
import { UserService } from '../../services/user/user.service';
import { Rol } from '../../interfaces/rol.interface';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-crud',
  standalone: true,
  imports: [],
  templateUrl: './user-crud.component.html',
  styleUrl: './user-crud.component.css'
})

export class UserCrudComponent implements OnInit {
  private userService = inject(UserService);
  usuarios: Usuario[] = [];
  roles: Rol[] = [];
  userForm: FormGroup;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder) {
    this.userForm = this.fb.group({
      nombre: ['', Validators.required],
      rol: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadUsuarios();
    this.loadRoles();
  }

  loadUsuarios() {
    this.userService.getAllUsuarios().subscribe(users => {
      this.usuarios = users;
    });
  }

  loadRoles() {
    // Aquí debes cargar los roles desde el backend si tienes un endpoint para ello
    this.roles = [
      { id: 1, nombre: 'ADMIN' },
      { id: 2, nombre: 'PLAYER' }
    ];
  }

  onEdit(user: Usuario) {
    this.userForm.patchValue({
      nombre: user.nombre,
      rol: user.rol.nombre
    });
  }

  onUpdate(user: Usuario) {
    const updatedUser = { ...user, rol: this.userForm.value.rol };
    this.userService['updateUsuarioRol'](user.idUsuario, updatedUser).subscribe({
      next: () => {
        this.errorMessage = null;
        this.loadUsuarios();
      },
      error: (error: { message: string | null; }) => {
        this.errorMessage = error.message;
      }
    });
  }

  onDelete(user: Usuario) {
    this.userService['deleteUsuario'](user.idUsuario).subscribe({
      next: () => {
        this.errorMessage = null;
        this.loadUsuarios();
      },
      error: (error: { message: string | null }) => {
        this.errorMessage = error.message;
      }
    });
  }
}

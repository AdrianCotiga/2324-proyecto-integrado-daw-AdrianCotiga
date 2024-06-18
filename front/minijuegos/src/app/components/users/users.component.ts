import { Component, OnInit, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
import { UserService } from '../../services/user/user.service';
import { Usuario } from '../../interfaces/user.interface';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faSort, faPenToSquare, faTrash } from '@fortawesome/free-solid-svg-icons';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [RouterModule, FontAwesomeModule, FormsModule],
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})

export class UsersComponent implements OnInit {
  private userService = inject(UserService);
  users: Usuario[] = [];
  usuariosFiltrados: Usuario[] = [];
  ordenarAscendente: boolean = true;
  faSort = faSort;
  faPenToSquare = faPenToSquare;
  faTrash = faTrash;
  filtro: string = '';

  ngOnInit(): void {
    this.cargarTodo();
  }

  cargarTodo() {
    this.userService.getAllUsuarios().subscribe(users => {
      this.users = users;
      this.usuariosFiltrados = users;
    });
  }

  ordenarPor(atributo: string) {
    this.ordenarAscendente = !this.ordenarAscendente;
    this.usuariosFiltrados.sort((a, b) => {
      const valorA = this.obtenerValor(a, atributo);
      const valorB = this.obtenerValor(b, atributo);

      if (valorA < valorB) {
        return this.ordenarAscendente ? -1 : 1;
      } else if (valorA > valorB) {
        return this.ordenarAscendente ? 1 : -1;
      } else {
        return 0;
      }
    });
  }

  obtenerValor(usuario: Usuario, atributo: string) {
    switch (atributo) {
      case 'idUsuario':
        return usuario.idUsuario;
      case 'nombre':
        return usuario.nombre.toLowerCase();
      case 'rol':
        return usuario.rol.nombre.toLowerCase();
      default:
        return '';
    }
  }

  filtrarUsuarios() {
    const filtroLower = this.filtro.toLowerCase();
    this.usuariosFiltrados = this.users.filter(user => 
      user.idUsuario.toString().includes(filtroLower) ||
      user.nombre.toLowerCase().includes(filtroLower) ||
      user.rol.nombre.toLowerCase().includes(filtroLower)
    );
  }

}

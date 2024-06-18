import { Usuario } from './user.interface';

export interface Perfil {
    idPerfil: number;
    usuario: Usuario;
    nivel: number;
    habilidad: number;
    partidas: number;
  }
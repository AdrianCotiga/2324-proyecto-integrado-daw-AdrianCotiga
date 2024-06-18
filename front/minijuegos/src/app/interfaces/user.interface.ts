import { Rol } from "./rol.interface";

export interface Usuario {
    idUsuario: number;
    nombre: string;
    rol: Rol;
}
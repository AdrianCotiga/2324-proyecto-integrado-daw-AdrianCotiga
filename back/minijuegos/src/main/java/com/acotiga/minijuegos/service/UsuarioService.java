package com.acotiga.minijuegos.service;

import java.util.List;

import com.acotiga.minijuegos.dto.UsuarioDto;
import com.acotiga.minijuegos.entity.Perfil;
import com.acotiga.minijuegos.entity.Usuario;

public interface UsuarioService {

	Usuario registrarUsuario(UsuarioDto usuarioDto);

	Usuario iniciarSesion(String nombre, String contra);

	boolean usuarioExists(String nombre);

	void cerrarSesion();

	List<Usuario> getAllUsuarios();

	Usuario updateUsuarioRol(Long id, UsuarioDto usuarioDto);

	void deleteUsuario(Long id);

	Perfil getPerfilByUsuarioId(Long id);
}

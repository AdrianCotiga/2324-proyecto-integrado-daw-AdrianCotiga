package com.acotiga.minijuegos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acotiga.minijuegos.dto.UsuarioDto;
import com.acotiga.minijuegos.entity.Perfil;
import com.acotiga.minijuegos.entity.Usuario;
import com.acotiga.minijuegos.service.UsuarioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(path = UsuarioController.BASE_URL)
public class UsuarioController {

	public static final String BASE_URL = "/admin";
	public static final String USERS_URL = "/users";
	public static final String ID_VARIABLE_URL = "/{idUsuario}";

	private final UsuarioService usuarioService;

	@GetMapping(value = UsuarioController.USERS_URL)
	public List<Usuario> getAllUsuarios() {
		return usuarioService.getAllUsuarios();
	}

	@PutMapping(value = UsuarioController.USERS_URL + ID_VARIABLE_URL)
	public ResponseEntity<Usuario> updateUsuarioRol(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
		Usuario updatedUsuario = usuarioService.updateUsuarioRol(id, usuarioDto);
		return ResponseEntity.ok(updatedUsuario);
	}

	@DeleteMapping(value = UsuarioController.USERS_URL + ID_VARIABLE_URL)
	public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
		usuarioService.deleteUsuario(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = UsuarioController.USERS_URL + "/perfil")
	public ResponseEntity<Perfil> getPerfil(@PathVariable Long id) {
		Perfil perfil = usuarioService.getPerfilByUsuarioId(id);
		return ResponseEntity.ok(perfil);
	}

}

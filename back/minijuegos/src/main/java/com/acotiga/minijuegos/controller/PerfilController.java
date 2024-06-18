package com.acotiga.minijuegos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acotiga.minijuegos.dto.PerfilDto;
import com.acotiga.minijuegos.service.PerfilService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(path = UsuarioController.BASE_URL)
public class PerfilController {

	public static final String BASE_URL = "/player";
	public static final String PROFILE_URL = "/profile";
	public static final String PROFILE_VARIACLE_URL = "/{id}";

	private final PerfilService perfilService;

	@GetMapping(value = PROFILE_URL)
	public ResponseEntity<PerfilDto> getUsuarioPerfil() {
		PerfilDto perfilDto = perfilService.getCurrentUsuarioPerfil();
		return new ResponseEntity<>(perfilDto, HttpStatus.OK);
	}

	@GetMapping(value = PROFILE_URL + PROFILE_VARIACLE_URL)
	public ResponseEntity<PerfilDto> getUsuarioPerfilById(@PathVariable Long id) {
		PerfilDto perfilDto = perfilService.getPerfilByUsuarioId(id);
		return new ResponseEntity<>(perfilDto, HttpStatus.OK);
	}
}

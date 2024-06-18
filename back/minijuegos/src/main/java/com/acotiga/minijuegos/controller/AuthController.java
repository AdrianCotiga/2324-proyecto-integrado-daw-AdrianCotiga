package com.acotiga.minijuegos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acotiga.minijuegos.dto.UsuarioDto;
import com.acotiga.minijuegos.entity.AuthenticationResponse;
import com.acotiga.minijuegos.entity.Usuario;
import com.acotiga.minijuegos.service.UsuarioService;
import com.acotiga.minijuegos.util.JwtUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserDetailsService userDetailsService;
	private UsuarioService usuarioService;
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UsuarioDto usuarioDto) {
		try {
			Usuario usuario = usuarioService.iniciarSesion(usuarioDto.getNombre(), usuarioDto.getContra());
			final UserDetails userDetails = userDetailsService.loadUserByUsername(usuarioDto.getNombre());
			final String jwt = jwtUtil.generateToken(userDetails);

			String redirectUrl;
			if (usuario.getRol().getNombre().equals("ADMIN")) {
				redirectUrl = "/admin/users";
			} else if (usuario.getRol().getNombre().equals("PLAYER")) {
				redirectUrl = "/player/profile";
			} else {
				redirectUrl = "/";
			}

			return ResponseEntity.ok(new AuthenticationResponse(jwt, redirectUrl));
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDto usuarioDto) {
		if (usuarioService.usuarioExists(usuarioDto.getNombre())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("El nombre de usuario ya está en uso");
		}
		Usuario usuario = usuarioService.registrarUsuario(usuarioDto);
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}
}

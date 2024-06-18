package com.acotiga.minijuegos.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.acotiga.minijuegos.dto.UsuarioDto;
import com.acotiga.minijuegos.entity.Perfil;
import com.acotiga.minijuegos.entity.Rol;
import com.acotiga.minijuegos.entity.Usuario;
import com.acotiga.minijuegos.repository.PerfilRepository;
import com.acotiga.minijuegos.repository.RolRepository;
import com.acotiga.minijuegos.repository.UsuarioRepository;
import com.acotiga.minijuegos.service.UsuarioService;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;
	private RolRepository rolRepository;
	private PerfilRepository perfilRepository;
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() {
		if (!usuarioRepository.existsByNombre("admin")) {
			Usuario admin = new Usuario();
			admin.setNombre("admin");
			admin.setContrasenya(passwordEncoder.encode("admin"));
			Rol rolAdmin = rolRepository.findByNombre("ADMIN").orElseThrow();
			admin.setRol(rolAdmin);
			usuarioRepository.save(admin);
		}
	}

	@Override
	public Usuario registrarUsuario(UsuarioDto usuarioDto) {

		LocalDate localDate = LocalDate.now();
		Usuario usuario = new Usuario();
		Rol rol = rolRepository.findById(2L).orElseThrow();
		Perfil perfil = new Perfil();

		usuario.setNombre(usuarioDto.getNombre());
		usuario.setContrasenya(passwordEncoder.encode(usuarioDto.getContra()));
		usuario.setRol(rol);
		Usuario savedUsuario = usuarioRepository.save(usuario);

		perfil.setUsuario(savedUsuario);
		perfil.setFechaCreacion(localDate);
		perfil.setNivel(1);
		perfil.setHabilidad(0);
		perfil.setPartidas(0);
		perfilRepository.save(perfil);

		return savedUsuario;
	}

	@Override
	public Usuario iniciarSesion(String nombre, String contra) {
		Usuario usuario = usuarioRepository.findByNombre(nombre)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario o contraseña incorrectos"));
		if (passwordEncoder.matches(contra, usuario.getContrasenya())) {
			return usuario;
		} else {
			throw new UsernameNotFoundException("Usuario o contraseña incorrectos");
		}
	}

	@Override
	public boolean usuarioExists(String nombre) {
		return usuarioRepository.existsByNombre(nombre);
	}

	@Override
	public void cerrarSesion() {
		SecurityContextHolder.clearContext();
	}

	@Override
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAllByOrderByIdUsuarioAsc();
	}

	public Usuario updateUsuarioRol(Long id, UsuarioDto usuarioDto) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		Usuario usuario = usuarioOptional.get();
		Optional<Rol> rolOptional = rolRepository.findByNombre(usuarioDto.getRol().getNombre());
		usuario.setRol(rolOptional.get());
		return usuarioRepository.save(usuario);
	}

	public Usuario saveUsuario(Usuario usuario) {
		Optional<Rol> rol = rolRepository.findByNombre(usuario.getRol().getNombre());
		usuario.setRol(rol.get());

		return usuarioRepository.save(usuario);
	}

	@Override
	public void deleteUsuario(Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		Perfil perfil = perfilRepository.findByUsuario(usuario)
				.orElseThrow(() -> new UsernameNotFoundException("Perfil no encontrado"));
		perfilRepository.delete(perfil);
		usuarioRepository.delete(usuario);
	}

	@Override
	public Perfil getPerfilByUsuarioId(Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		return perfilRepository.findByUsuario(usuario)
				.orElseThrow(() -> new UsernameNotFoundException("Perfil no encontrado"));
	}
}

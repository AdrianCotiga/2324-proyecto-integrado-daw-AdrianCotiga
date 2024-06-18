package com.acotiga.minijuegos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acotiga.minijuegos.dto.PerfilDto;
import com.acotiga.minijuegos.entity.Perfil;
import com.acotiga.minijuegos.entity.Usuario;
import com.acotiga.minijuegos.repository.PerfilRepository;
import com.acotiga.minijuegos.repository.UsuarioRepository;
import com.acotiga.minijuegos.service.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	public Usuario getCurrentUsuario() {
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		return usuarioRepository.findByNombre(currentUsername)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}

	@Override
	public PerfilDto getCurrentUsuarioPerfil() {
		Usuario currentUsuario = getCurrentUsuario();
		Perfil perfil = perfilRepository.findByUsuario(currentUsuario)
				.orElseThrow(() -> new UsernameNotFoundException("Perfil no encontrado para el usuario"));

		return new PerfilDto(perfil.getIdPerfil(), currentUsuario.getNombre(), perfil.getNivel(), perfil.getHabilidad(),
				perfil.getPartidas());
	}

	@Override
	public PerfilDto getPerfilByUsuarioId(Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		Perfil perfil = perfilRepository.findByUsuario(usuario)
				.orElseThrow(() -> new UsernameNotFoundException("Perfil no encontrado para el usuario"));

		return new PerfilDto(perfil.getIdPerfil(), usuario.getNombre(), perfil.getNivel(), perfil.getHabilidad(),
				perfil.getPartidas());
	}
}

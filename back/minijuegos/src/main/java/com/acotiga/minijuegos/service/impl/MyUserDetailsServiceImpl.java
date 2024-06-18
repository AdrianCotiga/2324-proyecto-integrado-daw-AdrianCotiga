package com.acotiga.minijuegos.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acotiga.minijuegos.entity.Usuario;
import com.acotiga.minijuegos.repository.UsuarioRepository;
import com.acotiga.minijuegos.service.MyUserDetailsService;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByNombre(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		return new org.springframework.security.core.userdetails.User(usuario.getNombre(), usuario.getContrasenya(),
				Collections.singleton(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre())));
	}
}
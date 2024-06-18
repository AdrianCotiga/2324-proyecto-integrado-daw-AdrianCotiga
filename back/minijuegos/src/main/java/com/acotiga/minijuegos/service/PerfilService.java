package com.acotiga.minijuegos.service;

import com.acotiga.minijuegos.dto.PerfilDto;
import com.acotiga.minijuegos.entity.Usuario;

public interface PerfilService {

	Usuario getCurrentUsuario();

	PerfilDto getCurrentUsuarioPerfil();

	PerfilDto getPerfilByUsuarioId(Long id);
}

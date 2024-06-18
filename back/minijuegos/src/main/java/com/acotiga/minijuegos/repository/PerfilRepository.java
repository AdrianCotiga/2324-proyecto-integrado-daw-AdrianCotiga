package com.acotiga.minijuegos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acotiga.minijuegos.entity.Perfil;
import com.acotiga.minijuegos.entity.Usuario;

@RepositoryRestResource
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	Optional<Perfil> findByUsuario(Usuario usuario);

}

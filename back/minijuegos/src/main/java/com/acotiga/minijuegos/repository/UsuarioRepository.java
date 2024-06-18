package com.acotiga.minijuegos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acotiga.minijuegos.entity.Usuario;

@RepositoryRestResource
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByNombre(String nombre);

	boolean existsByNombre(String nombre);

	List<Usuario> findAllByOrderByIdUsuarioAsc();
}

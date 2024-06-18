package com.acotiga.minijuegos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acotiga.minijuegos.entity.Rol;

@RepositoryRestResource
public interface RolRepository extends JpaRepository<Rol, Long> {

	Optional<Rol> findByNombre(String nombre);
}

package com.acotiga.minijuegos.dto;

import com.acotiga.minijuegos.entity.Rol;

import lombok.Data;

@Data
public class UsuarioDto {
	private String nombre;
	private String contra;
	private Rol rol;
}

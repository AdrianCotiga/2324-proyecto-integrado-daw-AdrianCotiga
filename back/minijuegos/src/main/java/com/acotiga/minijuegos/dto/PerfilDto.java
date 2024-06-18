package com.acotiga.minijuegos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PerfilDto {
	private Long idPerfil;
	private String nombreUsuario;
	private int nivel;
	private int habilidad;
	private int partidas;
}

package com.acotiga.minijuegos.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "partida", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_partida" }) })
public class Partida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_partida", unique = true, nullable = false)
	private Long idPartida;

	@OneToOne
	@JoinColumn(name = "fk_id_usuario")
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "fk_id_minijuego")
	private Minijuego minijuego;

	@Column(name = "fecha_creacion", unique = true, nullable = false)
	private LocalDateTime fechaCreacion;

	@Column(name = "concentracion")
	private int concentracion;

	@Column(name = "resultado")
	private int resultado;

}

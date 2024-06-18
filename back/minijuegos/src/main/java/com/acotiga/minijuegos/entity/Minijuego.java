package com.acotiga.minijuegos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "minijuego", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_minijuego" }) })
public class Minijuego {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_minijuego", unique = true, nullable = false)
	private Long idMinijuego;

	@Column(name = "nombre", nullable = false)
	private String nombre;

}

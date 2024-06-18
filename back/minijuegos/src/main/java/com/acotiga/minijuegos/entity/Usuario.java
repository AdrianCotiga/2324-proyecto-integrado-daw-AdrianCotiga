package com.acotiga.minijuegos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_usuario" }) })
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", unique = true, nullable = false)
	private Long idUsuario;

	@Column(name = "nombre", unique = true, nullable = false)
	private String nombre;

	@Column(name = "contrasenya", nullable = false)
	private String contrasenya;

	@ManyToOne
	@JoinColumn(name = "fk_id_rol")
	private Rol rol;
}

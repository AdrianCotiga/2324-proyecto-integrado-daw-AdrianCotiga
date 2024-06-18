package com.acotiga.minijuegos.entity;

import java.time.LocalDate;

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
@Table(name = "perfil", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_perfil" }) })
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil", unique = true, nullable = false)
	private Long idPerfil;

	@OneToOne
	@JoinColumn(name = "fk_id_usuario")
	private Usuario usuario;

	@Column(name = "fecha_creacion", unique = true, nullable = false)
	private LocalDate fechaCreacion;

	@Column(name = "nivel", nullable = false)
	private int nivel;

	@Column(name = "habilidad")
	private int habilidad;

	@Column(name = "partidas")
	private int partidas;

	@Column(name = "atencion")
	private int atencion;

	@Column(name = "logica")
	private int logica;

	@Column(name = "matematicas")
	private int matematicas;

	@Column(name = "memoria")
	private int memoria;

	@Column(name = "visual")
	private int visual;

	@Column(name = "suerte")
	private int suerte;

}

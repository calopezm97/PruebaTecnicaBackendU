package com.usuarios.prueba.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad con la que se va a realizar y hacer uso de los usuarios,
 * administrando de manera correcta
 * 
 * @author Carlos Lopez
 *
 */
@Entity
@Table(name = "usuario")
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	int id;
	@Column(length = 20)
	private String nombre;
	private String apellido;
	private Pais pais;
	private TipoDocumento tipoDocumento;
	private String documento;
	@Column(name = "email", length = 300)
	private String email;
	@Column(name = "state")
	private String estado;
	@Column(name = "created_at")
	private String creado;
	@Column(name = "update_at")
	private String actualizado;


}

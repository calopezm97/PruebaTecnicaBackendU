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
	@Column(name = "first_name", length = 20)
	private String firstName;
	@Column(name = "middle_name", length = 20)
	private String middleName;
	@Column(name = "last_name", length = 20)
	private String lastName;
	@Column(name = "second_last_name", length = 20)
	private String secondLastName;
	@Column(name = "country")
	private String country;
	@Column(name = "document_type")
	private String documentType;
	@Column(name = "document")
	private String document;
	@Column(name = "area")
	private String area;
	@Column(name = "email", length = 300)
	private String email;
	@Column(name = "state")
	private String state;
	@Column(name = "addmission_date")
	private String addmissionDate;
	@Column(name = "created_at")
	private String createdAt;
	@Column(name = "update_at")
	private String updateAt;


}

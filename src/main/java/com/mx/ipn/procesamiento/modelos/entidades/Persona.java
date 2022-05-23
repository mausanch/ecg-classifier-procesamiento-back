package com.mx.ipn.procesamiento.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "tipo"
)
@JsonSubTypes(
		{
			@JsonSubTypes.Type(value = Usuario.class, name = "Usuario"),
})
public class Persona implements Serializable {

	public Persona(Long idPersona, String nombres, String apellidoPaterno, String apellidoMaterno,
			String correoElectronico, Date fechaNacimiento,Integer sexo, String contrasena) {
		super();
		this.idPersona = idPersona;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.correoElectronico = correoElectronico;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.contrasena = contrasena;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersona;
	
	@Column(name = "nombre", nullable = false, length = 60)
	private String nombres;
	
	@Column(name = "apellido_paterno", nullable = false, length = 60)
	private String apellidoPaterno;

	@Column(name = "apellido_materno", nullable = false, length = 60)
	private String apellidoMaterno;
		
	@Column(name = "correo_electronico", nullable = false, length = 60)
	private String correoElectronico;

	@Column(name = "fecha_nacimiento", nullable = false)
	private Date fechaNacimiento;
	
	@Column(name = "sexo", nullable = false)
	private Integer sexo;
	
	@Column(name = "contrasena", nullable = false, length = 100)
	private String contrasena;
	
	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;
	
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;	
	
	
	@PrePersist
	private void antesPersistir()
	{
		this.fechaCreacion = new Date();
	}
	
	@PreUpdate
	private void antesActualizar()
	{
		this.fechaModificacion = new Date();
	}

	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombres=" + nombres + ", apellido_paterno=" + apellidoPaterno
				+ ", apellido_materno=" + apellidoMaterno + ", correo_electronico=" + correoElectronico
				+ ", contrasena=" + contrasena + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion="
				+ fechaModificacion + "]";
	}
	
	
}

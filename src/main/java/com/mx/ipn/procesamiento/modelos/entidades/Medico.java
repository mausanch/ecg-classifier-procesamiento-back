package com.mx.ipn.procesamiento.modelos.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "medicos")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Medico extends Persona {

	public Medico(Long idPersona, String nombres, String apellidoPaterno, String apellidoMaterno,
			String correoElectronico, Date fechaNacimiento, Integer sexo, String contrasena) {
		super(idPersona, nombres, apellidoPaterno, apellidoMaterno, correoElectronico, fechaNacimiento, sexo, contrasena);
	}

	private static final long serialVersionUID = 1L;

	@Column(name="cedula", nullable= false, length= 10)
	private String Cedula;
	
}

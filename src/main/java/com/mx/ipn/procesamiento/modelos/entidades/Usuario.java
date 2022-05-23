package com.mx.ipn.procesamiento.modelos.entidades;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Usuario extends Persona{

	public Usuario(Long idPersona, String nombres, String apellidoPaterno, String apellidoMaterno,
			String correoElectronico, Date fechaNacimiento,Integer sexo, String contrasena) {
		super(idPersona, nombres, apellidoPaterno, apellidoMaterno, correoElectronico, fechaNacimiento, sexo, contrasena);
	}

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private Set<AnalisisUsuario> analisislUsuario;
	
}

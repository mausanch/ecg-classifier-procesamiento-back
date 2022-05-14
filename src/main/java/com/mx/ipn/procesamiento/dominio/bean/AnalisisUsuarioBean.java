package com.mx.ipn.procesamiento.dominio.bean;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Builder
@ToString
@Slf4j
public class AnalisisUsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Tolerate
	public AnalisisUsuarioBean () {
		log.info("Creando AnalisisUsuarioBean");
	}

	@NotEmpty(message = "El idUsuario no puede ser nulo" )
	private String idUsuario;
	
	@Min(value = 0, message = "La edad no puede ser negativa")
	private Integer edad;
	
	@Min(value = 0, message = "El sexo solo puede ser 1 o 0")
    @Max(value = 1, message = "El sexo solo puede ser 1 o 0")
	private Integer sexo;
	
	@Min(value = 0, message = "El inicio de analisis no puede ser menor a 0")
	@NotEmpty(message = "El sexo no puede ser nulo" )
	private Integer inicioAnalisis;
	
	@NotEmpty(message = "El fin de analisis no puede ser nulo" )
	private Integer finAnalisis;
	
	@Min(value = 1, message = "El intervalo debe ser positivo y mayor de 0")
	@NotEmpty(message = "El intervalo no puede ser nulo" )
	private Integer intervaloAnalisis;
	
	@NotEmpty(message = "El intervalo no puede ser nulo") 
	private byte [] electrocardiograma;
}

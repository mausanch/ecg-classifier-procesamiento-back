package com.mx.ipn.procesamiento.dominio.vo;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ClasificacionesVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private String apellidoPaterno;
	
	private String apellidoMaterno;
	
	private String clasificacion;
	
	private Integer inicioAnalisis;
	
	private Integer finAnalisis;
	
	private Integer intervaloAnalisis;
}

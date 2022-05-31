package com.mx.ipn.procesamiento.dominio.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AnalisisUsuarioVo {

	private Long idAnalisis;
	
	private Integer inicioAnalisis;
	
	private Integer finAnalisis;
	
	private Integer intervaloAnalisis;
	
	private String electrocardiograma;
	
	private String resultado;

	private Long idUsuario;
}

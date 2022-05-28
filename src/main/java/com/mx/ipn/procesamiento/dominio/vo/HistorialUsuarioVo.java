package com.mx.ipn.procesamiento.dominio.vo;

import java.io.Serializable;
import java.util.Date;

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
public class HistorialUsuarioVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idAnalisis;
	private Date fechaAnalisis;
	private String duracion;
	//private String resultado;
}

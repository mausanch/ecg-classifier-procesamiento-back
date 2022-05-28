package com.mx.ipn.procesamiento.dominio.vo;



import java.io.Serializable;
import java.util.List;

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
public class ClasificacionVo implements  Serializable{

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String apellidoMaterno;
	private String apellidoPaterno;
	private Integer edad;
	private Integer sexo;
	private List<ElectrocardiogramaVo> ecg; 
	
}

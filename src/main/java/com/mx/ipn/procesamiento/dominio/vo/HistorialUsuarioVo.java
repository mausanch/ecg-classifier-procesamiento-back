package com.mx.ipn.procesamiento.dominio.vo;

import java.io.Serializable;
import java.time.LocalDate;

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
public class HistorialUsuarioVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idAnalisis;
	private LocalDate fechaAnalisis;
	private Integer duracion;
}

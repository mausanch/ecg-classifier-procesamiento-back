package com.mx.ipn.procesamiento.dominio.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class ElectrocardiogramaVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name; // Clasificación
	private List<SeriesVo> series;
}

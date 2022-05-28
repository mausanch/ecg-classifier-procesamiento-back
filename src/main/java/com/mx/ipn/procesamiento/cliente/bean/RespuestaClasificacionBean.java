package com.mx.ipn.procesamiento.cliente.bean;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

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
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RespuestaClasificacionBean implements Serializable {


	private static final long serialVersionUID = 1L;

	@Tolerate
	public RespuestaClasificacionBean () {
		log.debug("Creando RespuestaClasificacionBean");
	}
	
	private Integer clasificacion;
}

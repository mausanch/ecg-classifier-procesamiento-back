package com.mx.ipn.procesamiento.cliente.bean;

import java.io.Serializable;

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
public class RespuestaClasificacionBean implements Serializable {


	private static final long serialVersionUID = 1L;

	@Tolerate
	public RespuestaClasificacionBean () {
		log.debug("Creando RespuestaClasificacionBean");
	}
	
	private Integer clasificacion;
}

package com.mx.ipn.procesamiento.dominio.bean;

import java.io.Serializable;
import java.util.List;

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
public class AnalisisUsuariosBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Tolerate
	public AnalisisUsuariosBean () {
		log.info(("Creando AnalisisUsuariosBean"));
	}
	
	@NotEmpty(message = "La lista no puede ser vacia" )
	private List <AnalisisUsuarioBean> analisisUsuariosBean;
}

package com.mx.ipn.procesamiento.cliente.bean;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PerfilSinteticoBean implements Serializable {


	private static final long serialVersionUID = 1L;	
	private Integer sexo;
	private Integer edad;
	private Integer ritmo_cardiaco;

}

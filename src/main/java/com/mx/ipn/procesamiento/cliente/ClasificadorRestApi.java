package com.mx.ipn.procesamiento.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mx.ipn.procesamiento.cliente.bean.PerfilSinteticoBean;
import com.mx.ipn.procesamiento.cliente.bean.RespuestaClasificacionBean;

@FeignClient(value= "ClasificadorApiCliente", url = "http://localhost:8090", primary = false)
public interface ClasificadorRestApi {

	@PostMapping(value="/")
	public RespuestaClasificacionBean obtenerClasificacion (@RequestBody PerfilSinteticoBean clasificacionBean); 
	
}

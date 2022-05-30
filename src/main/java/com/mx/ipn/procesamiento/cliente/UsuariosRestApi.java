package com.mx.ipn.procesamiento.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mx.ipn.procesamiento.cliente.bean.DatosPersonalesBean;

@FeignClient(value= "UsuariosApiCliente", url = "http://localhost:8081/usuarios", primary = false)
public interface UsuariosRestApi {
	
	@GetMapping(value="/usuario/datos-personales-analisis/{id_usuario}")
	public DatosPersonalesBean obtenerDatosPersonales (@PathVariable("id_usuario") String idUsuario); 
}

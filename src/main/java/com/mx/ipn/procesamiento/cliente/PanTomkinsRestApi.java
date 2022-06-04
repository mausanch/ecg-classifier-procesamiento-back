package com.mx.ipn.procesamiento.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mx.ipn.procesamiento.cliente.bean.ECGBean;
import com.mx.ipn.procesamiento.cliente.bean.RespuestaPanTomkinsBean;

@FeignClient(value= "PanTomkinsApiCliente", url = "http://localhost:81", primary = false)
public interface PanTomkinsRestApi {

	@PostMapping(value="/procesar-ecg")
	public RespuestaPanTomkinsBean obtenerRitmoCardiaco (@RequestBody ECGBean ecgBean); 

}

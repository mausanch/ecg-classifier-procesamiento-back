package com.mx.ipn.procesamiento.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ipn.procesamiento.cliente.ClasificadorRestApi;
import com.mx.ipn.procesamiento.cliente.bean.ClasificacionBean;
import com.mx.ipn.procesamiento.dominio.bean.AnalisisUsuarioBean;
import com.mx.ipn.procesamiento.dominio.bean.AnalisisUsuariosBean;
import com.mx.ipn.procesamiento.dominio.vo.ClasificacionVo;
import com.mx.ipn.procesamiento.dominio.vo.ElectrocardiogramaVo;
import com.mx.ipn.procesamiento.dominio.vo.RespuestaAnalisisVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/procesamiento")
public class ProcesamientoControlador {
	
	@Autowired
	private ClasificadorRestApi clasificadorRestApi;
	
	@PostMapping(value = "/clasificacion-personal")
	public ResponseEntity<RespuestaAnalisisVo> clasificarPersonal (@Valid @RequestBody AnalisisUsuarioBean analisisUsuarioBean){
		log.info("<----- Inicio petición ----->");
		
		ResponseEntity <RespuestaAnalisisVo> resultado = null;
		
		ClasificacionBean clasificadorBean= new ClasificacionBean(); 
		clasificadorBean.setEdad(22);
		clasificadorBean.setRitmo_cardiaco(200);
		clasificadorBean.setSexo(1);
		
		Integer resultado1 = clasificadorRestApi.obtenerClasificacion(clasificadorBean);
		
		log.info(resultado1.toString());
		
		
		log.info("---Fin petición controlador Usuario----");
		return resultado;
	}
	

	@PostMapping(value = "/clasificacion-lotes")
	public ResponseEntity<ClasificacionVo> clasificarLotes (@Valid @RequestBody AnalisisUsuariosBean analisisUsuariosBean){
		log.info("<----- Inicio petición ----->");
		
		ResponseEntity <ClasificacionVo> resultado = null;
		
		
		log.info("---Fin petición controlador Usuario----");
		return resultado;
	}
	
	@GetMapping(value = "/pruebaServicios")
	public String prueba (){
		log.info("<----- Inicio petición ----->");
		Integer resultado1=null;
		
		ClasificacionBean clasificadorBean= new ClasificacionBean(); 
		clasificadorBean.setEdad(22);
		clasificadorBean.setRitmo_cardiaco(200);
		clasificadorBean.setSexo(1);		
		
		clasificadorRestApi.obtenerClasificacion(clasificadorBean);
		
		log.info("---Fin petición controlador Usuario----");
		return resultado1.toString();
	}
}

package com.mx.ipn.procesamiento.controlador;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ipn.procesamiento.dominio.bean.AnalisisUsuarioBean;
import com.mx.ipn.procesamiento.dominio.bean.AnalisisUsuariosBean;
import com.mx.ipn.procesamiento.dominio.vo.RespuestaAnalisisVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/procesamiento")
public class ProcesamientoControlador {
	
	@PostMapping(value = "/clasificacion-personal")
	public ResponseEntity<RespuestaAnalisisVo> clasificarPersonal (@Valid @RequestBody AnalisisUsuarioBean analisisUsuarioBean){
		log.info("<----- Inicio petición ----->");
		
		ResponseEntity <RespuestaAnalisisVo> resultado = null;
		
		
		log.info("---Fin petición controlador Usuario----");
		return resultado;
	}
	

	@PostMapping(value = "/clasificacion-lotes")
	public ResponseEntity<RespuestaAnalisisVo> clasificarLotes (@Valid @RequestBody AnalisisUsuariosBean analisisUsuariosBean){
		log.info("<----- Inicio petición ----->");
		
		ResponseEntity <RespuestaAnalisisVo> resultado = null;
		
		
		log.info("---Fin petición controlador Usuario----");
		return resultado;
	}
	
}

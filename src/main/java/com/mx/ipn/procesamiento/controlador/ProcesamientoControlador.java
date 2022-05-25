package com.mx.ipn.procesamiento.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.mx.ipn.procesamiento.cliente.ClasificadorRestApi;
import com.mx.ipn.procesamiento.cliente.PanTomkinsRestApi;
import com.mx.ipn.procesamiento.cliente.UsuariosRestApi;
import com.mx.ipn.procesamiento.cliente.bean.PerfilSinteticoBean;
import com.mx.ipn.procesamiento.cliente.bean.DatosPersonalesBean;
import com.mx.ipn.procesamiento.cliente.bean.ECGBean;
import com.mx.ipn.procesamiento.cliente.bean.RespuestaClasificacionBean;
import com.mx.ipn.procesamiento.cliente.bean.RespuestaPanTomkinsBean;
import com.mx.ipn.procesamiento.dominio.bean.AnalisisUsuarioBean;
import com.mx.ipn.procesamiento.dominio.bean.AnalisisUsuariosBean;
import com.mx.ipn.procesamiento.dominio.vo.ClasificacionVo;
import com.mx.ipn.procesamiento.dominio.vo.RespuestaAnalisisVo;

import feign.Request;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/procesamiento")
public class ProcesamientoControlador {
	
	@Autowired
	private ClasificadorRestApi clasificadorRestApi;
	
	@Autowired 
	private UsuariosRestApi usuariosRestApi;
	
	@Autowired
	private PanTomkinsRestApi panTomkinsRestApi;
	
	@CrossOrigin
	@PostMapping(value = "/perfil-sintetico")
	public ResponseEntity<RespuestaClasificacionBean> clasificarPersonal (@RequestBody PerfilSinteticoBean clasificacionBean){
		log.info("<----- Inicio petición ----->");
				
		ResponseEntity <RespuestaClasificacionBean> resultado=null;
	
		RespuestaClasificacionBean respuestaClasificacionBean = null;
		
		respuestaClasificacionBean = clasificadorRestApi.obtenerClasificacion(clasificacionBean);
		
		resultado = new ResponseEntity <> (respuestaClasificacionBean, HttpStatus.OK);
		
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
	
	@PostMapping(value = "/pan-tomkins-api-prueba")
	public ResponseEntity<RespuestaPanTomkinsBean> panTomkinsPrueba (@RequestBody ECGBean ecgBean){
		log.info("<----- Inicio petición ----->");
				
		ResponseEntity <RespuestaPanTomkinsBean> resultado=null;
	
		RespuestaPanTomkinsBean respuestaPanTomkinsBean = null;
		
		respuestaPanTomkinsBean = panTomkinsRestApi.obtenerClasificacion(ecgBean);
		
		resultado = new ResponseEntity <> (respuestaPanTomkinsBean, HttpStatus.OK);
		
		log.info("---Fin petición controlador Usuario----");
		return resultado;
	}
	
	@GetMapping(value = "/obtener-datos-personales-prueba")
	public  ResponseEntity<DatosPersonalesBean> pruebaFeign (){
		log.info("<----- Inicio petición ----->");

		ResponseEntity <DatosPersonalesBean> resultado=null;
		
		DatosPersonalesBean datosPersonalesBean = null; 
		
		datosPersonalesBean = usuariosRestApi.obtenerDatosPersonales("2");
		
		resultado = new ResponseEntity <> (datosPersonalesBean, HttpStatus.OK);
		
		log.info("---Fin petición controlador Usuario----");
		return resultado;
	}
	
	@PostMapping(value = "/clasificacion-personal-prueba")
	public ResponseEntity<RespuestaAnalisisVo> clasificarPersonalPrueba (@Valid @RequestBody AnalisisUsuarioBean analisisUsuarioBean){
		log.info("<----- Inicio petición ----->");
		
		ResponseEntity <RespuestaAnalisisVo> resultado = null;
		
		PerfilSinteticoBean clasificadorBean= new PerfilSinteticoBean(); 
		clasificadorBean.setEdad(22);
		clasificadorBean.setRitmo_cardiaco(200);
		clasificadorBean.setSexo(1);
		
		/*
		Integer resultado1 = clasificadorRestApi.obtenerClasificacion(clasificadorBean);
		
		log.info(resultado1.toString());
		*/
		
		log.info("---Fin petición controlador Usuario----");
		return resultado;
	}
	
	
}

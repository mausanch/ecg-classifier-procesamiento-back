package com.mx.ipn.procesamiento.controlador;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.mx.ipn.procesamiento.dominio.vo.ListaHistorialVo;

import lombok.extern.slf4j.Slf4j;

import java.lang.Math;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/procesamiento")
public class ProcesamientoControlador {
	
	@Autowired
	private ClasificadorRestApi clasificadorRestApi;
	
	@Autowired 
	private UsuariosRestApi usuariosRestApi;
	
	@Autowired
	private PanTomkinsRestApi panTomkinsRestApi;
	
	@PostMapping(value = "/perfil-sintetico")
	public ResponseEntity<RespuestaClasificacionBean> clasificacionPerfilSintetico (@RequestBody PerfilSinteticoBean perfilSinteticoBean){
		log.info("<----- Inicio petición ----->");
				
		ResponseEntity <RespuestaClasificacionBean> resultado=null;
	
		RespuestaClasificacionBean respuestaClasificacionBean = null;
		
		respuestaClasificacionBean = clasificadorRestApi.obtenerClasificacion(perfilSinteticoBean);
		
		resultado = new ResponseEntity <> (respuestaClasificacionBean, HttpStatus.OK);
		
		log.info("---Fin petición---");
		return resultado;
	}
	
	@PostMapping(value = "/clasificacion-personal")
	public ResponseEntity<RespuestaClasificacionBean> clasificacionPersonal (@RequestBody AnalisisUsuarioBean analisisUsuarioBean){
		log.info("<----- Inicio petición Clsificacion----->");
	
		log.info(analisisUsuarioBean.toString());
		
		ResponseEntity <RespuestaClasificacionBean> resultado=null;
		RespuestaPanTomkinsBean respuestaPanTomkinsBean = null;
		RespuestaClasificacionBean respuestaClasificacionBean = null;
		ECGBean ecgBean= new ECGBean();
		
		log.info("<----- Llamado a API Usuarios----->");
		DatosPersonalesBean datosPersonalesBean= usuariosRestApi.obtenerDatosPersonales(analisisUsuarioBean.getIdUsuario());

		PerfilSinteticoBean perfilSinteticoBean = new PerfilSinteticoBean ();
		perfilSinteticoBean.setEdad(datosPersonalesBean.getEdad());
		perfilSinteticoBean.setSexo(datosPersonalesBean.getSexo());
		
		int inicioAnalisis;
		int finAnalisis;
		if (analisisUsuarioBean.getIsMinutosInicioFin()) {
			inicioAnalisis = analisisUsuarioBean.getInicioAnalisis()*60*360;
			finAnalisis = analisisUsuarioBean.getFinAnalisis()*360;
		}else {
			inicioAnalisis = analisisUsuarioBean.getInicioAnalisis()*360;
			finAnalisis = analisisUsuarioBean.getFinAnalisis()*360;
		}
		
		
		int intervaloAnalisis;
		if (analisisUsuarioBean.getIsMinutosIntervalo()) {
			
			intervaloAnalisis= analisisUsuarioBean.getIntervaloAnalisis()*60*360;
		}else {
			intervaloAnalisis= analisisUsuarioBean.getIntervaloAnalisis()*360;
		}
		
		log.info("<---------------------------------------------------------------------->");
		log.info("InicioAnalisis:"+String.valueOf(inicioAnalisis));
		log.info("FinAnalisis:"+String.valueOf(finAnalisis));
		log.info("IntervaloAnalisis:"+String.valueOf(intervaloAnalisis));
		
		String electrocardiograma []  = analisisUsuarioBean.getElectrocardiograma();
		String electrocardiogramaAnalisis [];
		
		int segmentoAnalisis=1;
		
		for (int indice =inicioAnalisis; indice<= finAnalisis; indice++) {
		
			if (indice==(intervaloAnalisis*segmentoAnalisis)) {
				electrocardiogramaAnalisis = Arrays.copyOfRange(electrocardiograma, (segmentoAnalisis-1)*intervaloAnalisis, indice);
				ecgBean.setElectrocardiograma(electrocardiogramaAnalisis);
				log.info(ecgBean.getElectrocardiograma().toString());
				
				log.info("<----- Llamado a API Pan Tomkins----->");
				respuestaPanTomkinsBean = panTomkinsRestApi.obtenerRitmoCardiaco(ecgBean);
				log.info("Ritmo Cardiaco:"+String.valueOf(respuestaPanTomkinsBean.getRitmo_cardiaco()));
				log.info("<----- Llamado a API Clasificacion----->");
				perfilSinteticoBean.setRitmo_cardiaco(Math.round(respuestaPanTomkinsBean.getRitmo_cardiaco()));		
				log.info(perfilSinteticoBean.toString());
				respuestaClasificacionBean= clasificadorRestApi.obtenerClasificacion(perfilSinteticoBean);
				log.info("Clasificación:"+respuestaClasificacionBean.getClasificacion().toString());
				log.info(String.valueOf(respuestaPanTomkinsBean.getRitmo_cardiaco()));
				
				
				
				
				segmentoAnalisis++;
			}
		}
						
		resultado = new ResponseEntity <> (respuestaClasificacionBean, HttpStatus.OK);
		
		log.info("---Fin petición Clasificacion---");
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
		
		respuestaPanTomkinsBean = panTomkinsRestApi.obtenerRitmoCardiaco(ecgBean);
		
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
		
		log.info("---Fin petición controlador Usuario----");
		return resultado;
	}
	
	@GetMapping("/historial/{id_usuario}")
	public ResponseEntity <ListaHistorialVo> historialUsuario (@PathVariable("id_usuario") String idUsuario){
		
		ResponseEntity <ListaHistorialVo> resultado=null;
		
		ListaHistorialVo RespuestaInicioVo = null;
		
		resultado = new ResponseEntity <> (RespuestaInicioVo, HttpStatus.OK);
		
		return resultado;
	}
	
}

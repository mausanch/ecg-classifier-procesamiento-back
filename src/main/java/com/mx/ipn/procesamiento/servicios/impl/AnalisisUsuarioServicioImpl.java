package com.mx.ipn.procesamiento.servicios.impl;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ipn.procesamiento.cliente.UsuariosRestApi;
import com.mx.ipn.procesamiento.dominio.bean.AnalisisUsuarioBean;
import com.mx.ipn.procesamiento.dominio.vo.AnalisisUsuarioVo;
import com.mx.ipn.procesamiento.dominio.vo.HistorialUsuarioInterfaceVo;
import com.mx.ipn.procesamiento.dominio.vo.ListaHistorialVo;
import com.mx.ipn.procesamiento.modelos.entidades.AnalisisUsuario;
import com.mx.ipn.procesamiento.modelos.entidades.Usuario;
import com.mx.ipn.procesamiento.modelos.repositorios.AnalisisUsuarioRepositorio;
import com.mx.ipn.procesamiento.servicios.AnalisisUsuarioServicio;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnalisisUsuarioServicioImpl implements AnalisisUsuarioServicio{
	
	@Autowired
	private AnalisisUsuarioRepositorio analisisUsuarioRepositorio;
	
	@Autowired
	private UsuariosRestApi usuarioRestApi;
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	@Transactional
	public AnalisisUsuario guardarAnalisisUsuario(AnalisisUsuarioBean analisisUsuarioBean, String resultado) {
		
		log.info ("<--- Inicio servicio Guardar analisis--->");
		
		byte [] electrocardiogramaByte = Arrays.toString(analisisUsuarioBean.getElectrocardiograma()).getBytes(StandardCharsets.UTF_8);
		byte [] resultadoByte = resultado.toString().getBytes();
		
		log.info("String:" +analisisUsuarioBean.getElectrocardiograma().toString());
		
		Usuario usuario= null;
		
		usuario=usuarioRestApi.obtenerReferenciaUsuario(analisisUsuarioBean.getIdUsuario());
		
		log.info(analisisUsuarioBean.toString());
		AnalisisUsuario analisisUsuario = new AnalisisUsuario();
		analisisUsuario.setInicioAnalisis(analisisUsuarioBean.getInicioAnalisis());
		analisisUsuario.setFinAnalisis(analisisUsuarioBean.getFinAnalisis());
		analisisUsuario.setIntervaloAnalisis(analisisUsuarioBean.getIntervaloAnalisis());
		analisisUsuario.setUsuario(usuario);
		analisisUsuario.setResultado(resultadoByte);
		analisisUsuario.setElectrocardiograma(electrocardiogramaByte);
		
		log.info (analisisUsuario.toString());
		
		usuario.getAnalisislUsuario().add(analisisUsuario);
		
		entityManager.merge(analisisUsuario); 
		
		log.info ("<--- Fin servicio Guardar analisis--->");
		return null;
	}

	@Override
	public AnalisisUsuarioVo recuperarAnalisisUsuarioById(Long idAnalisisUsuario) {
		log.info ("<--- Inicio servicio recuperar Analisis Usuario By ID--->");
		
		log.info(idAnalisisUsuario.toString());
		
		Optional<AnalisisUsuario> optionalAnalisisUsuario = analisisUsuarioRepositorio.findById(idAnalisisUsuario);
						
		AnalisisUsuario analisisUsuario=null;
		AnalisisUsuarioVo analisisUsuarioVo=null;
		
		
		
		if (optionalAnalisisUsuario.isEmpty()) {
			log.info("Sin resultados");
			return null;
		}
		
		analisisUsuario= optionalAnalisisUsuario.get();
		
		
		
		//String electrocardiograma = new String (Arrays.toString(analisisUsuario.getElectrocardiograma()));
		String electrocardiograma = null;
		String resultados = new String (analisisUsuario.getResultado());
		
		analisisUsuarioVo = new AnalisisUsuarioVo();
		analisisUsuarioVo.setIdAnalisis(analisisUsuario.getIdAnalisis());
		analisisUsuarioVo.setInicioAnalisis(analisisUsuario.getInicioAnalisis());
		analisisUsuarioVo.setFinAnalisis(analisisUsuario.getFinAnalisis());
		analisisUsuarioVo.setIntervaloAnalisis(analisisUsuario.getInicioAnalisis());
		analisisUsuarioVo.setElectrocardiograma(electrocardiograma);
		analisisUsuarioVo.setResultado(resultados);
		analisisUsuarioVo.setIdUsuario(analisisUsuario.getUsuario().getIdPersona());
		
		log.info ("<--- Fin servicio recuperar Analisis Usuario By ID--->");
		return analisisUsuarioVo;
	}

	@Override
	public ListaHistorialVo recuperarHistorialAnalisisUsuario(Long idUsuario) {
		
		log.info ("<--- Inicio servicio recupear Historial Analisis--->");
		List<HistorialUsuarioInterfaceVo> listaAnalisisUsuario = analisisUsuarioRepositorio.findAnalisisUsuarioByIdUsuario(idUsuario);
		
		if (listaAnalisisUsuario.isEmpty()){
			return null;
		}
		
		log.info(listaAnalisisUsuario.toString());
		
		ListaHistorialVo listHistorialVo = new ListaHistorialVo ();
		
		listHistorialVo.setHistorial(listaAnalisisUsuario);
		log.info ("<--- Fin servicio recupear Historial Analisis--->");
		return listHistorialVo;
	}

}

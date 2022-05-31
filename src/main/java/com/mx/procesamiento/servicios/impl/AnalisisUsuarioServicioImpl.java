package com.mx.procesamiento.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.ipn.procesamiento.dominio.bean.AnalisisUsuarioBean;
import com.mx.ipn.procesamiento.dominio.vo.AnalisisUsuarioVo;
import com.mx.ipn.procesamiento.dominio.vo.HistorialUsuarioVo;
import com.mx.ipn.procesamiento.dominio.vo.ListaHistorialVo;
import com.mx.ipn.procesamiento.modelos.entidades.AnalisisUsuario;
import com.mx.ipn.procesamiento.modelos.entidades.Usuario;
import com.mx.ipn.procesamiento.modelos.repositorios.AnalisisUsuarioRepositorio;
import com.mx.procesamiento.servicios.AnalisisUsuarioServicio;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnalisisUsuarioServicioImpl implements AnalisisUsuarioServicio{
	
	@Autowired
	private AnalisisUsuarioRepositorio analisisUsuarioRepositorio;

	@Override
	public AnalisisUsuario guardarAnalisisUsuario(AnalisisUsuarioBean analisisUsuarioBean, String resultado) {
		
		byte [] electrocardiogramaByte = analisisUsuarioBean.getElectrocardiograma().toString().getBytes();
		byte [] resultadoByte = resultado.toString().getBytes();
		
		
		AnalisisUsuario analisisUsuario = new AnalisisUsuario();
		Usuario usuario = new Usuario ();
		usuario.setIdPersona(Long.valueOf(analisisUsuarioBean.getIdUsuario()));
		analisisUsuario.setInicioAnalisis(analisisUsuarioBean.getInicioAnalisis());
		analisisUsuario.setFinAnalisis(analisisUsuario.getFinAnalisis());
		analisisUsuario.setIntervaloAnalisis(analisisUsuario.getIntervaloAnalisis());
		analisisUsuario.setUsuario(usuario);
		analisisUsuario.setResultado(resultadoByte);
		analisisUsuario.setElectrocardiograma(electrocardiogramaByte);
		
		analisisUsuario = analisisUsuarioRepositorio.save(analisisUsuario);
		return analisisUsuario;
	}

	@Override
	public AnalisisUsuarioVo recuperarAnalisisUsuarioById(Long idAnalisisUsuario) {
		
		Optional<AnalisisUsuario> optionalAnalisisUsuario = analisisUsuarioRepositorio.findById(String.valueOf(idAnalisisUsuario));
		AnalisisUsuario analisisUsuario=null;
		AnalisisUsuarioVo analisisUsuarioVo=null;
		
		if (optionalAnalisisUsuario.isPresent()) {
			return null;
		}
		
		analisisUsuario= optionalAnalisisUsuario.get();
		
		String electrocardiograma = new String (analisisUsuario.getElectrocardiograma());
		String resultados = new String (analisisUsuario.getResultado());
		
		analisisUsuarioVo = new AnalisisUsuarioVo();
		analisisUsuarioVo.setIdAnalisis(analisisUsuario.getIdAnalisis());
		analisisUsuarioVo.setInicioAnalisis(analisisUsuario.getInicioAnalisis());
		analisisUsuarioVo.setFinAnalisis(analisisUsuario.getFinAnalisis());
		analisisUsuarioVo.setIntervaloAnalisis(analisisUsuario.getInicioAnalisis());
		analisisUsuarioVo.setElectrocardiograma(electrocardiograma);
		analisisUsuarioVo.setResultado(resultados);
		analisisUsuarioVo.setIdUsuario(analisisUsuario.getUsuario().getIdPersona());

		return analisisUsuarioVo;
	}

	@Override
	public ListaHistorialVo recuperarHistorialAnalisisUsuario(Long idUsuario) {
		
		List<AnalisisUsuario> listaAnalisisUsuario = analisisUsuarioRepositorio.findAnalisisUsuarioByIdUsuario(String.valueOf(idUsuario));
		
		if (listaAnalisisUsuario.isEmpty()){
			return null;
		}
		
		ListaHistorialVo listHistorialVo = new ListaHistorialVo ();
		
		List<HistorialUsuarioVo> listaHistorialVo = new ArrayList<>();
		
		for (AnalisisUsuario analisisUsuario :listaAnalisisUsuario ) {
			HistorialUsuarioVo historialUsuarioVo = new HistorialUsuarioVo();

			historialUsuarioVo.setIdAnalisis(analisisUsuario.getIdAnalisis());
			historialUsuarioVo.setFechaAnalisis(analisisUsuario.getFechaCreacion());
			historialUsuarioVo.setDuracion(analisisUsuario.getFinAnalisis()-analisisUsuario.getInicioAnalisis());
			listaHistorialVo.add(historialUsuarioVo);
			
		}
		
		listHistorialVo.setHistorial(listaHistorialVo);
		
		return listHistorialVo;
	}

}

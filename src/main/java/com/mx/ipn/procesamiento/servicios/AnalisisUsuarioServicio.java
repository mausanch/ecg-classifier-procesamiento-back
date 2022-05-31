package com.mx.ipn.procesamiento.servicios;

import com.mx.ipn.procesamiento.dominio.bean.AnalisisUsuarioBean;
import com.mx.ipn.procesamiento.dominio.vo.AnalisisUsuarioVo;
import com.mx.ipn.procesamiento.dominio.vo.ListaHistorialVo;
import com.mx.ipn.procesamiento.modelos.entidades.AnalisisUsuario;

public interface AnalisisUsuarioServicio {
	
	public AnalisisUsuario guardarAnalisisUsuario (AnalisisUsuarioBean analisisUsuarioBean, String resultado);
	
	public AnalisisUsuarioVo recuperarAnalisisUsuarioById (Long idAnalisisUsuario);
	
	public ListaHistorialVo recuperarHistorialAnalisisUsuario (Long idUsuario);

}

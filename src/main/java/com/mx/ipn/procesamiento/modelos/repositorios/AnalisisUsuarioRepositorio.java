package com.mx.ipn.procesamiento.modelos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mx.ipn.procesamiento.dominio.vo.HistorialUsuarioInterfaceVo;
import com.mx.ipn.procesamiento.modelos.entidades.AnalisisUsuario;


public interface AnalisisUsuarioRepositorio extends JpaRepository<AnalisisUsuario,Long>, AnalisisUsuarioRepositorioCustom {

	
	@Query(value="select au.id_analisis as idAnalisis, au.fecha_creacion as fechaAnalisis, (au.inicio_analisis - au.fin_analisis) as duracion from analisis_usuario au where usuario_id = ?1"
			,nativeQuery = true)
	List<HistorialUsuarioInterfaceVo> findAnalisisUsuarioByIdUsuario(Long idUsuario);
	
}

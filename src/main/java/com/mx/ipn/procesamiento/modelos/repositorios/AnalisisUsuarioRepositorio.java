package com.mx.ipn.procesamiento.modelos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mx.ipn.procesamiento.modelos.entidades.AnalisisUsuario;


public interface AnalisisUsuarioRepositorio extends JpaRepository<AnalisisUsuario,String>, AnalisisUsuarioRepositorioCustom {

	
	@Query("select id_analisis, fecha_analisis, inicio_analisis, fin_analisis from analisis_usuario where usuario_id = ?1")
	List<AnalisisUsuario> findAnalisisUsuarioByIdUsuario(String idUsuario);
	
}

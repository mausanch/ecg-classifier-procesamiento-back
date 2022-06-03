package com.mx.ipn.procesamiento.dominio.vo;

import java.time.LocalDate;

public interface HistorialUsuarioInterfaceVo {

	Long getIdAnalisis();
	LocalDate getFechaAnalisis();
	Integer getDuracion();
}

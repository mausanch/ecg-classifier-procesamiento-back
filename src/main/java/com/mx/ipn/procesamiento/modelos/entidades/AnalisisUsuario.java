package com.mx.ipn.procesamiento.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "analisis_usuario")
public class AnalisisUsuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAnalisis;
	
	@Column(name= "inicio_analisis", nullable=false)
	private Integer inicioAnalisis;
	
	@Column(name="fin_analisis", nullable=false)
	private Integer finAnalisis;
	
	@Column(name="intervalo_analisis",nullable=false)
	private Integer intervaloAnalisis;
	
	@Lob 
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="electrocardiograma", columnDefinition="bytea", nullable=true)
	private byte [] electrocardiograma;
	
	@Lob 
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="resultado", columnDefinition="bytea", nullable=true)
	private byte [] resultado;
	
	
	@ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_1_USUARIO_ID"))
	private Usuario usuario;
	
	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;
	
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;	
	
	
	@PrePersist
	private void antesPersistir()
	{
		this.fechaCreacion = new Date();
	}
	
	@PreUpdate
	private void antesActualizar()
	{
		this.fechaModificacion = new Date();
	}

	
}

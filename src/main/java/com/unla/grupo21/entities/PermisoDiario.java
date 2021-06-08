package com.unla.grupo21.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Diario")
public class PermisoDiario extends Permiso{

	@Column
	private String motivo;

	public PermisoDiario() {
		super();
	}

	public PermisoDiario(int idPermiso, Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta, String motivo) {
		super(idPermiso, pedido, fecha, desdeHasta);
		this.motivo = motivo;
	}

	public PermisoDiario(Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta, String motivo) {
		super(pedido, fecha, desdeHasta);
		this.motivo = motivo;
	}
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	
}

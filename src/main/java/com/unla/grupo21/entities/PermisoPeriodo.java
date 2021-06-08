package com.unla.grupo21.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="Periodo")
public class PermisoPeriodo extends Permiso{

	@Column
	private int cantDias;
	@Column
	private boolean vacaciones;
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Rodado rodado;
	
	public PermisoPeriodo() {}

	public PermisoPeriodo(int idPermiso, Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta,
			int cantDias, boolean vacaciones, Rodado rodado) {
		super(idPermiso, pedido, fecha, desdeHasta);
		this.cantDias = cantDias;
		this.vacaciones = vacaciones;
		this.rodado = rodado;
	}

	public PermisoPeriodo(Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta,
			int cantDias, boolean vacaciones, Rodado rodado) {
		super(pedido, fecha, desdeHasta);
		this.cantDias = cantDias;
		this.vacaciones = vacaciones;
		this.rodado = rodado;
	}
	
	public int getCantDias() {
		return cantDias;
	}

	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}

	public boolean isVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(boolean vacaciones) {
		this.vacaciones = vacaciones;
	}

	public Rodado getRodado() {
		return rodado;
	}

	public void setRodado(Rodado rodado) {
		this.rodado = rodado;
	}
}

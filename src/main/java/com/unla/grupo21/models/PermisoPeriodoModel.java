package com.unla.grupo21.models;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PermisoPeriodoModel extends PermisoModel{

	@NotNull(message= "La cantidad de días no puede ser nula")
	@Min(2)
	@Max(20)
	private Integer cantDias;
	
	private boolean vacaciones;
	
	@NotNull
	@Valid
	private RodadoModel rodado;
	
	public PermisoPeriodoModel() {
		rodado = new RodadoModel();
	}

	public PermisoPeriodoModel(PersonaModel pedido, LocalDate fecha,
			Set<LugarModel> desdeHasta, int cantDias, boolean vacaciones, RodadoModel rodado) {
		super(pedido, fecha, desdeHasta);
		setCantDias(cantDias);
		this.vacaciones = vacaciones;
		this.rodado = rodado;
	}
	
	public PermisoPeriodoModel(int idPermiso, PersonaModel pedido, LocalDate fecha,
			Set<LugarModel> desdeHasta, int cantDias, boolean vacaciones, RodadoModel rodado) {
		super(idPermiso, pedido, fecha, desdeHasta);
		setCantDias(cantDias);
		this.vacaciones = vacaciones;
		this.rodado = rodado;
	}

	public Integer getCantDias() {
		if(cantDias==null) {
			return null;
		}
		return cantDias.intValue();
	}

	public void setCantDias(int cantDias) {
		this.cantDias = Integer.valueOf(cantDias);
	}

	public boolean isVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(boolean vacaciones) {
		this.vacaciones = vacaciones;
	}

	public RodadoModel getRodado() {
		return rodado;
	}

	public void setRodado(RodadoModel rodado) {
		this.rodado = rodado;
	}

	@Override
	public String toString() {
		return super.toString() + "PermisoPeriodoModel [cantDias=" + cantDias + ", vacaciones=" + vacaciones + ", rodado=" + rodado + "]";
	}
	
	
}

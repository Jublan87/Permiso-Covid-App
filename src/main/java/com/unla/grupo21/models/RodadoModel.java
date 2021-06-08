package com.unla.grupo21.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RodadoModel {
	private int id;
	
	@NotNull(message = "El dominio no puede ser nulo")
	@Pattern(regexp = "^([A-Z]{3}[0-9]{3}$)|^([A-Z]{2}[0-9]{3}[A-Z]{2}$)", message = "El formato del dominio es incorrecto")
	private String dominio;

	@NotEmpty
	@Size(min=6, max=45, message = "La descripción del vehículo contener entre 6 y 45 caracteres")
	private String vehiculo;
	
	public RodadoModel() {}
	
	
	public RodadoModel(int id, String dominio, String vehiculo) {
		super();
		this.id = id;
		this.dominio = dominio;
		this.vehiculo = vehiculo;
	}


	public RodadoModel(String dominio, String vehiculo) {
		super();
		this.dominio = dominio;
		this.vehiculo = vehiculo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}


	@Override
	public String toString() {
		return "RodadoModel [id=" + id + ", dominio=" + dominio + ", vehiculo=" + vehiculo + "]";
	}

	
	
}

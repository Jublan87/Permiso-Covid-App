package com.unla.grupo21.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;

@Entity
@Table(name = "rodado")
public class Rodado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "dominio", nullable = false, unique=true)
	private String dominio;

	@Column(name = "vehiculo", nullable = false)
	private String vehiculo;

	@Column(name = "createdat", nullable = false, updatable = false)
	@CreationTimestamp
	@ReadOnlyProperty
	private LocalDateTime createdAt;

	@Column(name = "updatedat")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public Rodado() {
	}

	public Rodado(int id, String dominio, String vehiculo) {
		super();
		this.id = id;
		setDominio(dominio);
		this.vehiculo = vehiculo;
	}

	public Rodado(String dominio, String vehiculo) {
		super();
		setDominio(dominio);
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
		this.dominio = dominio.toUpperCase();
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

}

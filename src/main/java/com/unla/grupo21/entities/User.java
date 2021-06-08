package com.unla.grupo21.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.unla.grupo21.models.TipoDocumento;

@Entity
@Table(name="user")
public class User extends Persona{
	
	@Column(name="username", unique=true, nullable= false, length=45)
	private String username;
	
	@Column(name="password", nullable=false, length= 60)
	private String password;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserRole userRole;
	
	@Column(name="activo")
	private boolean activo;
	
	public User() {}


	public User(String nombre, String apellido, TipoDocumento tipoDocumento, long documento, String email,
			String username, String password, UserRole userRole, boolean activo) {
		super(nombre, apellido, tipoDocumento, documento, email);
		this.username = username;
		this.password = password;
		this.userRole = userRole;
		this.activo = activo;
	}

	public User(int id, String nombre, String apellido, TipoDocumento tipoDocumento, long documento, String email,
			String username, String password, UserRole userRole, boolean activo) {
		super(id, nombre, apellido, tipoDocumento, documento, email);
		this.username = username;
		this.password = password;
		this.userRole = userRole;
		this.activo = activo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}

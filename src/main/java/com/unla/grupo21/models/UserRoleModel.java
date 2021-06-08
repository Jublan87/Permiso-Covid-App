package com.unla.grupo21.models;

import javax.validation.constraints.NotNull;

public class UserRoleModel {

	private int id;
	
	@NotNull
	private String role;
	
	private boolean activo = true;
	
	public UserRoleModel(){};
	
	public UserRoleModel(int id, String role, boolean activo)
	{
		setId(id);
		setRole(role);
		setActivo(activo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "UserRoleModel [id=" + id + ", role=" + role + ", activo=" + activo + "]";
	}
	

}

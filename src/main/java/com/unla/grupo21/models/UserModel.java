package com.unla.grupo21.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserModel extends PersonaModel {

	@NotNull
	@Size(min=6, max=45, message = "El nombre de usuario debe contener entre 6 y 18 caracteres")
	private String username;
	
	@NotNull
	@Size(min=6, max=60, message = "La contraseña debe contener entre 6 y 18 caracteres")
	private String password;
	
	private UserRoleModel userRole;
	
	private boolean activo = true;
	
	public UserModel(){};

	public UserModel(int id, String nombre, String apellido, TipoDocumento tipoDocumento, long documento, String email,
			String username, String password, UserRoleModel userRole, boolean activo) {
		super(id, nombre, apellido, tipoDocumento, Long.valueOf(documento), email);
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

	public UserRoleModel getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleModel userRole) {
		this.userRole = userRole;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return super.toString() + "UserModel [username=" + username + ", password=" + password + ", userRole=" + userRole + ", activo="
				+ activo + "]";
	}

	
	
}

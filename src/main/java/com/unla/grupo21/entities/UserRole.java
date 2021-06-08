package com.unla.grupo21.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="user_role")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="role", unique=true, nullable=false, length=100)
	private String role;
	
	@Column(name="activo")
	private boolean activo;
	
	@Column(name="createdat", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdat;
	
	@Column(name="updatedat")
	@UpdateTimestamp
	private LocalDateTime updatedat;
	
	public UserRole() {};
	
	public UserRole(int id, String role, boolean activo)
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

	public LocalDateTime getCreatedat() {
		return createdat;
	}

	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}

	public LocalDateTime getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(LocalDateTime updatedat) {
		this.updatedat = updatedat;
	}
	
	
	
}

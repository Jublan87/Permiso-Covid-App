package com.unla.grupo21.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PersonaModel {

	private int id;
	
	@NotEmpty(message = "El nombre no puede ser nulo")
	private String nombre;
	
	@NotEmpty(message = "El apellido no puede ser nulo")
	private String apellido;
	
	@NotNull(message = "El tipo de documento no puede ser nulo")
	private TipoDocumento tipoDocumento;
	
	@NotNull(message = "El documento no puede ser nulo")
	@Min(1000000)
	@Max(50000000)
	private Long documento;
	
	@NotNull
	@Email(message = "El email debe ser válido")
	private String email;
	
	public PersonaModel() {}

	public PersonaModel(int id, String nombre, String apellido, TipoDocumento tipoDocumento, Long documento, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.email = email;
	}

	public PersonaModel(String nombre, String apellido, TipoDocumento tipoDocumento, Long documento, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "PersonaModel [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", tipoDocumento="
				+ tipoDocumento + ", documento=" + documento + ", email=" + email + "]";
	};
	
}

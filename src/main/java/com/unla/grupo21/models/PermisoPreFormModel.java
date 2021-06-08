package com.unla.grupo21.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PermisoPreFormModel {

	//Esta clase está pensada para validar los campos que se ingresan previo a la solicitud de permiso
	
	@Pattern(regexp = "^$|^([A-Za-z]{3}[0-9]{3}$)|^([A-Za-z]{2}[0-9]{3}[A-Za-z]{2}$)", message = "El formato del dominio es incorrecto")
	private String dominio;
	
	@NotNull(message = "El tipo de documento no puede ser nulo")
	private TipoDocumento tipoDocumento;
	
	@NotNull(message = "El documento no puede ser nulo")
	@Min(1000000)
	@Max(50000000)
	private Long documento;
	
	private boolean esDiario = true;

	public PermisoPreFormModel() {}

	public PermisoPreFormModel(
			@NotNull(message = "El dominio no puede ser nulo") @Pattern(regexp = "^([A-Za-z]{3}[0-9]{3}$)|^([A-Za-z]{2}[0-9]{3}[A-Za-z]{2}$)", message = "El formato del dominio es incorrecto") String dominio,
			@NotNull(message = "El tipo de documento no puede ser nulo") TipoDocumento tipoDocumento,
			@NotNull(message = "El documento no puede ser nulo") @Min(1000000) @Max(50000000) Long documento,
			boolean esDiario) {
		super();
		this.dominio = dominio;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.esDiario = esDiario;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
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

	public boolean isEsDiario() {
		return esDiario;
	}

	public void setEsDiario(boolean esDiario) {
		this.esDiario = esDiario;
	}

	

	
}

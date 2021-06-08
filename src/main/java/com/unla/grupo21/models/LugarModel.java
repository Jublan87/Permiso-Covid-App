package com.unla.grupo21.models;

import javax.validation.constraints.NotNull;

public class LugarModel {
    //
    private int id;

    @NotNull(message = "El lugar no puede ser nulo")
    private String lugar;

    @NotNull(message = "El código postal no puede ser nulo")
    private String codigoPostal;

    public LugarModel() {
    }

    public LugarModel(int id, String lugar, String codigoPostal) {
        this.id = id;
        this.lugar = lugar;
        this.codigoPostal = codigoPostal;
    }

    public LugarModel(String lugar, String codigoPostal) {
        this.lugar = lugar;
        this.codigoPostal = codigoPostal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LugarModel other = (LugarModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LugarModel [id=" + id + ", lugar=" + lugar + ", codigoPostal=" + codigoPostal + "]";
	}
    
    
}

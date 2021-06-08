package com.unla.grupo21.entities;

import javax.persistence.*;

@Entity
@Table(name = "lugar")
public class Lugar {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lugar", nullable = false)
    private String lugar;

    @Column(name = "codigopostal", nullable = false)
    private String codigoPostal;

    public Lugar() {
    }

    public Lugar(int id, String lugar, String codigoPostal) {
        this.id = id;
        this.lugar = lugar;
        this.codigoPostal = codigoPostal;
    }

    public Lugar(String lugar, String codigoPostal) {
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
		Lugar other = (Lugar) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
    
}

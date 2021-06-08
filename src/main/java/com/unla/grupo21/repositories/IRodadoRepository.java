package com.unla.grupo21.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo21.entities.Rodado;

@Repository("rodadoRepository")
public interface IRodadoRepository extends JpaRepository<Rodado, Serializable>{

	public abstract Rodado findById(int id);	
	public abstract Rodado findByDominio(String dominio);	
	public abstract Rodado findByVehiculo(String vehiculo);
	
}

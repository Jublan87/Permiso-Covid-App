package com.unla.grupo21.services;

import java.util.List;

import com.unla.grupo21.models.RodadoModel;

public interface IRodadoService {
	
	public RodadoModel findById(int id);
	public RodadoModel findByDominio(String dominio);
	public List<RodadoModel> getAll();	
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel);
	
}

package com.unla.grupo21.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo21.converters.RodadoConverter;
import com.unla.grupo21.entities.Rodado;
import com.unla.grupo21.models.RodadoModel;
import com.unla.grupo21.repositories.IRodadoRepository;
import com.unla.grupo21.services.IRodadoService;

@Service("rodadoService")
public class RodadoService implements IRodadoService{
	
	@Autowired
	@Qualifier("rodadoRepository")
	private IRodadoRepository rodadoRepository;
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;
	
	@Override
	public RodadoModel findById(int id) {
		RodadoModel rm = null;
		Rodado r = rodadoRepository.findById(id);
		if(r!=null) rm = rodadoConverter.entityToModel(r);
		return rm;
	}

	@Override
	public RodadoModel findByDominio(String dominio) {
		RodadoModel rm = null;
		Rodado r = rodadoRepository.findByDominio(dominio.toUpperCase());
		if(r != null) rm = rodadoConverter.entityToModel(r);
		return rm;
	}

	@Override
	public List<RodadoModel> getAll() {
		List<RodadoModel> lstRodados = new ArrayList<RodadoModel>();
		
		for(Rodado r : rodadoRepository.findAll()) {
			lstRodados.add( rodadoConverter.entityToModel(r) );
		}
		return lstRodados;
	}

	@Override
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel) {
		Rodado rodado = rodadoRepository.save(rodadoConverter.modelToEntity(rodadoModel));
		return rodadoConverter.entityToModel(rodado);
	}
	
}

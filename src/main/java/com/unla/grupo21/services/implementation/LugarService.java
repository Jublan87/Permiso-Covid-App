package com.unla.grupo21.services.implementation;

import com.unla.grupo21.converters.LugarConverter;
import com.unla.grupo21.entities.Lugar;
import com.unla.grupo21.models.LugarModel;
import com.unla.grupo21.repositories.ILugarRepository;
import com.unla.grupo21.services.ILugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("lugarService")
public class LugarService implements ILugarService {

    @Autowired
    @Qualifier("lugarRepository")
    private ILugarRepository lugarRepository;

    @Autowired
    @Qualifier("lugarConverter")
    private LugarConverter lugarConverter;

    @Override
    public LugarModel findById(int id) {
        Lugar l = lugarRepository.findById(id);
        return lugarConverter.entityToModel(l);
    }

    @Override
    public List<LugarModel> getAll() {
        List<LugarModel> lstLugares = new ArrayList<>();

        for (Lugar l: lugarRepository.findAll()) {
            lstLugares.add(lugarConverter.entityToModel(l));
        }

        return lstLugares;
    }
    
	@Override
	public List<LugarModel> getAllOrderByLugar() {
        List<LugarModel> lstLugares = new ArrayList<>();

        for (Lugar l: lugarRepository.findAllByOrderByLugarAsc()) {
            lstLugares.add(lugarConverter.entityToModel(l));
        }

        return lstLugares;
	}

    @Override
    public LugarModel insertOrUpdate(LugarModel lugarModel) {
        Lugar l = lugarRepository.save(lugarConverter.modelToEntity(lugarModel));
        return lugarConverter.entityToModel(l);
    }

}
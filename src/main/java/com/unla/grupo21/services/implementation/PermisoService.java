package com.unla.grupo21.services.implementation;

import com.unla.grupo21.converters.PermisoConverter;
import com.unla.grupo21.entities.Permiso;
import com.unla.grupo21.entities.PermisoPeriodo;
import com.unla.grupo21.models.*;
import com.unla.grupo21.repositories.IPermisoRepository;
import com.unla.grupo21.services.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("permisoService")
public class PermisoService implements IPermisoService {

    @Autowired
    @Qualifier("permisoRepository")
    private IPermisoRepository permisoRepository;

    @Autowired
    @Qualifier("permisoConverter")
    private PermisoConverter permisoConverter;

    @Override
    public PermisoModel findById(int id) {

        return permisoConverter.entityToModel(permisoRepository.findByIdPermiso(id));
    }

    @Override
    public List<PermisoModel> getAllByPerson(PersonaModel personaModel) {
        List<PermisoModel> lstPermiso = new ArrayList<PermisoModel>();
        List<Permiso> lstQuery = permisoRepository.getAllByIdPersona(personaModel.getId());
        
        if(lstQuery!=null) {
            for (Permiso p : lstQuery) {
                lstPermiso.add(permisoConverter.entityToModel(p));
            }
        }

        return lstPermiso;
    }

    @Override
    public List<PermisoModel> getAllByRodado(RodadoModel rodadoModel) {
        List<PermisoModel> lstPermiso = new ArrayList<PermisoModel>();
        List<PermisoPeriodo> lstQuery = permisoRepository.getAllByIdRodado(rodadoModel.getId());
        
        if(lstQuery!=null) {
            for (Permiso p : lstQuery) {
                lstPermiso.add(permisoConverter.entityToModel(p));
            }
        }

        return lstPermiso;
    }

    @Override
    public List<PermisoModel> getAllBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<PermisoModel> lstPermiso = new ArrayList<PermisoModel>();
        List<Permiso> lstQuery = permisoRepository.getAllPermisosBetweenDates(startDate, endDate);

        if(lstQuery!=null) {
            for (Permiso p : lstQuery) {
                lstPermiso.add(permisoConverter.entityToModel(p));
            }
        }

        return lstPermiso;
    }

    @Override
    public List<PermisoModel> getAllBetweenDatesAndPlaces(LocalDate startDate, LocalDate endDate, LugarModel lugarModel, boolean desde) {
        List<PermisoModel> lstPermiso = new ArrayList<PermisoModel>();
        List<Permiso> lstQuery = permisoRepository.getAllPermisosBetweenDates(startDate, endDate);

        if(lstQuery!=null) {
            for (Permiso p : lstQuery) {
                lstPermiso.add(permisoConverter.entityToModel(p));
            }

            if (desde) {
                lstPermiso.removeIf(permisoModel -> !new ArrayList<>(permisoModel.getDesdeHasta()).get(0).getLugar().equals(lugarModel.getLugar()));
            } else {
                lstPermiso.removeIf(permisoModel -> !new ArrayList<>(permisoModel.getDesdeHasta()).get(1).getLugar().equals(lugarModel.getLugar()));
            }
        }

        return lstPermiso;
    }

    @Override
    public PermisoModel insertOrUpdate(PermisoModel permisoModel) {
        Permiso permiso = permisoRepository.save(permisoConverter.modelToEntity(permisoModel));
        return permisoConverter.entityToModel(permiso);
    }
}

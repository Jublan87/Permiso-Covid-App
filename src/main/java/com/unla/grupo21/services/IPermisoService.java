package com.unla.grupo21.services;

import com.unla.grupo21.models.LugarModel;
import com.unla.grupo21.models.PermisoModel;
import com.unla.grupo21.models.PersonaModel;
import com.unla.grupo21.models.RodadoModel;

import java.time.LocalDate;
import java.util.List;

public interface IPermisoService {

    PermisoModel findById(int id);
    List<PermisoModel> getAllByPerson(PersonaModel personaModel);
    List<PermisoModel> getAllByRodado(RodadoModel rodadoModel);
    List<PermisoModel> getAllBetweenDates(LocalDate startDate, LocalDate endDate);
    List<PermisoModel> getAllBetweenDatesAndPlaces(LocalDate startDate, LocalDate endDate, LugarModel lugarModel, boolean desde);
    PermisoModel insertOrUpdate(PermisoModel permisoModel);
}

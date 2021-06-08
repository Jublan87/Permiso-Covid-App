package com.unla.grupo21.converters;

import com.unla.grupo21.entities.Lugar;
import com.unla.grupo21.models.LugarModel;
import org.springframework.stereotype.Component;

@Component("lugarConverter")
public class LugarConverter {
    //
    public LugarModel entityToModel(Lugar lugar) {
        return new LugarModel(lugar.getId(), lugar.getLugar(), lugar.getCodigoPostal());
    }

    public Lugar modelToEntity(LugarModel lugarModel) {
        return new Lugar(lugarModel.getId(), lugarModel.getLugar(), lugarModel.getCodigoPostal());
    }
}

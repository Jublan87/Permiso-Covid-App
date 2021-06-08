package com.unla.grupo21.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.grupo21.entities.Persona;
import com.unla.grupo21.entities.User;
import com.unla.grupo21.models.PersonaModel;
import com.unla.grupo21.models.UserModel;

@Component("personaConverter")
public class PersonaConverter {

	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	public PersonaModel entityToModel(Persona persona) {
		PersonaModel pm = null;
		
		if(persona instanceof User) {
			pm = userConverter.entityToModel((User)persona);
		} else {
			pm = new PersonaModel(persona.getId(), persona.getNombre(), persona.getApellido(), persona.getTipoDocumento(), persona.getDocumento(), persona.getEmail());
		}
		
		return pm;
	}
	
	public Persona modelToEntity(PersonaModel personaModel) {
		Persona p = null;
		
		if(personaModel instanceof UserModel) {
			p = userConverter.modelToEntity((UserModel)personaModel);
		} else {
			p = new Persona(personaModel.getId(), personaModel.getNombre(), personaModel.getApellido(), personaModel.getTipoDocumento(), personaModel.getDocumento(), personaModel.getEmail());
		}
		
		return p;
	}
	
}

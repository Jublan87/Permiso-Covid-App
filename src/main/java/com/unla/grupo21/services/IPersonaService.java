package com.unla.grupo21.services;

import java.util.List;

import com.unla.grupo21.models.PersonaModel;
import com.unla.grupo21.models.TipoDocumento;

public interface IPersonaService {

	public PersonaModel findById(int id);
	public PersonaModel findByTipoDocumentoAndDocumento(TipoDocumento tipoDocumento, long documento);
	public List<PersonaModel> getAll();
	public PersonaModel insertOrUpdate(PersonaModel personaModel);
}

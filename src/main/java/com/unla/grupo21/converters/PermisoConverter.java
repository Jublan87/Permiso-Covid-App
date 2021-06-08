package com.unla.grupo21.converters;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.grupo21.entities.Lugar;
import com.unla.grupo21.entities.Permiso;
import com.unla.grupo21.entities.PermisoDiario;
import com.unla.grupo21.entities.PermisoPeriodo;
import com.unla.grupo21.models.LugarModel;
import com.unla.grupo21.models.PermisoDiarioModel;
import com.unla.grupo21.models.PermisoModel;
import com.unla.grupo21.models.PermisoPeriodoModel;

@Component("permisoConverter")
public class PermisoConverter {

	@Autowired
	@Qualifier("lugarConverter")
	private LugarConverter lugarConverter;
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter; 
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;
	
	public PermisoModel entityToModel(Permiso permiso)
	{
		Set<LugarModel> lstLugares = new LinkedHashSet<LugarModel>();
		Iterator<Lugar> itr = permiso.getDesdeHasta().iterator();
		while(itr.hasNext()){
			  lstLugares.add(lugarConverter.entityToModel(itr.next()));
		}
		
		PermisoModel pm = null;
		
		if(permiso instanceof PermisoPeriodo) {
			PermisoPeriodo pp = (PermisoPeriodo) permiso;
			pm = new PermisoPeriodoModel(pp.getIdPermiso(), personaConverter.entityToModel(pp.getPedido()),
					pp.getFecha(), lstLugares, pp.getCantDias(), pp.isVacaciones(), 
					rodadoConverter.entityToModel(pp.getRodado()));
		}
		
		if(permiso instanceof PermisoDiario) {
			PermisoDiario pd = (PermisoDiario) permiso;
			pm = new PermisoDiarioModel(pd.getIdPermiso(), personaConverter.entityToModel(pd.getPedido()), pd.getFecha(), 
					lstLugares, pd.getMotivo());
		}
		
		return pm;
	}
	
	public Permiso modelToEntity(PermisoModel permisoModel)
	{
		Set<Lugar> lstLugares = new LinkedHashSet<Lugar>();
		Iterator<LugarModel> itr = permisoModel.getDesdeHasta().iterator();
		while(itr.hasNext()){
			  lstLugares.add(lugarConverter.modelToEntity(itr.next()));
		}
		
		Permiso p = null;
		
		if(permisoModel instanceof PermisoPeriodoModel) {
			PermisoPeriodoModel ppm = (PermisoPeriodoModel) permisoModel;
			p = new PermisoPeriodo(ppm.getIdPermiso(), personaConverter.modelToEntity(ppm.getPedido()), 
					ppm.getFecha(), lstLugares, ppm.getCantDias(), ppm.isVacaciones(),
					rodadoConverter.modelToEntity(ppm.getRodado()));
		}
		
		if(permisoModel instanceof PermisoDiarioModel) {
			PermisoDiarioModel pdm = (PermisoDiarioModel) permisoModel;
			p = new PermisoDiario(pdm.getIdPermiso(), 
					personaConverter.modelToEntity(pdm.getPedido()), pdm.getFecha(), lstLugares,
					pdm.getMotivo());
		}
		
		return p;
	}
	
}

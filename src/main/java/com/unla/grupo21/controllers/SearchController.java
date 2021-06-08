package com.unla.grupo21.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo21.helpers.Funciones;
import com.unla.grupo21.helpers.ViewRouteHelper;
import com.unla.grupo21.models.BuscarModel;
import com.unla.grupo21.models.LugarModel;
import com.unla.grupo21.models.PermisoModel;
import com.unla.grupo21.models.PermisoPreFormModel;
import com.unla.grupo21.models.PersonaModel;
import com.unla.grupo21.models.RodadoModel;
import com.unla.grupo21.models.TipoDocumento;
import com.unla.grupo21.services.ILugarService;
import com.unla.grupo21.services.IPermisoService;
import com.unla.grupo21.services.IPersonaService;
import com.unla.grupo21.services.IRodadoService;

@Controller
@RequestMapping("/permiso/buscar")
public class SearchController {
	
	@Autowired
	@Qualifier("permisoService")
	private IPermisoService permisoService;
	
	@Autowired
	@Qualifier("lugarService")
	private ILugarService lugarService;
	
	@Autowired
	@Qualifier("personaService")
	private IPersonaService personaService;
	
	@Autowired
	@Qualifier("rodadoService")
	private IRodadoService rodadoService;
	
	// POR FECHAS
	
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/porfechas")
	public ModelAndView searchByDate() {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_BUSCAR);
		BuscarModel buscar = new BuscarModel();
		buscar.setLugaresModel(lugarService.getAllOrderByLugar());
		mAV.addObject("model", buscar);

		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@PostMapping("/activeDates")
	public ModelAndView activeBetweenDates(@ModelAttribute(name = "model") BuscarModel model){

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_RESULTS);
		List<PermisoModel> lstPermisos = permisoService.getAllBetweenDates(model.getStartDate(), model.getEndDate());
		mAV.addObject("lstPermisos", lstPermisos);
		mAV.addObject("searchDesc", "Permisos activos entre " + Funciones.traerFechaCorta(model.getStartDate()) + " y " + Funciones.traerFechaCorta(model.getEndDate()));

		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@PostMapping("/activeDatesPlaces")
	public ModelAndView activeBetweenDatesAndPlaces(@ModelAttribute(name = "model") BuscarModel model) {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_RESULTS);
		LugarModel lugar = lugarService.findById(model.getLugarModel());
		List<PermisoModel> lstPermisos = permisoService.getAllBetweenDatesAndPlaces(model.getStartDate(), model.getEndDate(), lugar, model.isDesde());
		mAV.addObject("lstPermisos", lstPermisos);
		mAV.addObject("searchDesc", "Permisos activos entre " + Funciones.traerFechaCorta(model.getStartDate())  + " y " + Funciones.traerFechaCorta(model.getEndDate())
					  + " con " + (model.isDesde() ? "origen en " : "destino a ") + StringUtils.capitalize(lugar.getLugar().toLowerCase()));

		return mAV;
	}

	
	// POR RODADO
	
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/porrodado")
	public ModelAndView searchByRodado() {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_BUSCAR_RODADO);
		mAV.addObject("model", new PermisoPreFormModel());

		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@PostMapping("/porrodado")
	public ModelAndView searchByRodadoPost(@Valid @ModelAttribute(name = "model") PermisoPreFormModel model, BindingResult bindingResult){

		ModelAndView mV;
		
		if(bindingResult.hasFieldErrors("dominio")) {
			mV = new ModelAndView(ViewRouteHelper.PERMISO_BUSCAR_RODADO);
		} else {
			mV = new ModelAndView(ViewRouteHelper.PERMISO_RESULTS);
			RodadoModel rm = rodadoService.findByDominio(model.getDominio());
			if(rm==null) {
				mV.addObject("searchDesc", "Permisos asociados a la patente " + model.getDominio().toUpperCase());
			}else {
				mV.addObject("searchDesc", "Permisos asociados al vehículo " + rm.getVehiculo() + " con la patente " + rm.getDominio());
				mV.addObject("lstPermisos", permisoService.getAllByRodado(rm));
			}
		}
		return mV;
	}
	
	
	//POR PERSONA
	
	@GetMapping("/porpersona")
	public ModelAndView consultar()
	{
		ModelAndView mv = new ModelAndView(ViewRouteHelper.PERMISO_BUSCAR_PERSONA);
		List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
		mv.addObject("lstTipoDoc", lstTipoDoc);
		mv.addObject("ppfm", new PermisoPreFormModel());
		return mv;
	}
	
	@PostMapping("/porpersona")
	public ModelAndView traer(@Valid @ModelAttribute(name = "ppfm") PermisoPreFormModel p, BindingResult bindingResult)
	{
		ModelAndView mav;
		
		if(bindingResult.hasFieldErrors("documento")) {
			mav = new ModelAndView(ViewRouteHelper.PERMISO_BUSCAR_PERSONA);
			List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
			mav.addObject("lstTipoDoc", lstTipoDoc);
		} else {
			mav = new ModelAndView(ViewRouteHelper.PERMISO_RESULTS);
			PersonaModel persona = personaService.findByTipoDocumentoAndDocumento(p.getTipoDocumento(), p.getDocumento());
			if(persona!=null) {
				List<PermisoModel> pm = permisoService.getAllByPerson(persona);
				mav.addObject("lstPermisos", pm);
				mav.addObject("searchDesc", "Permisos asociados a " + persona.getNombre() + " " + persona.getApellido() + ", " + persona.getTipoDocumento() + " " + persona.getDocumento());
			} else {
				mav.addObject("searchDesc", "Permisos asociados al documento " + p.getTipoDocumento() + " " + p.getDocumento() );
			}
		}
	
		return mav;
	}
	
}

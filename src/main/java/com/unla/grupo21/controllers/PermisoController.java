package com.unla.grupo21.controllers;

import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo21.helpers.ViewRouteHelper;
import com.unla.grupo21.helpers.ZXingHelper;
import com.unla.grupo21.models.PermisoDiarioModel;
import com.unla.grupo21.models.PermisoModel;
import com.unla.grupo21.models.PermisoPeriodoModel;
import com.unla.grupo21.models.PermisoPreFormModel;
import com.unla.grupo21.models.PersonaModel;
import com.unla.grupo21.models.RodadoModel;
import com.unla.grupo21.models.TipoDocumento;
import com.unla.grupo21.services.ILugarService;
import com.unla.grupo21.services.IPermisoService;
import com.unla.grupo21.services.IPersonaService;
import com.unla.grupo21.services.IRodadoService;

@Controller
@RequestMapping("/permiso")
public class PermisoController {

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

	
	
	@GetMapping("/new")
	public ModelAndView solicitud() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_NEW);
	
		List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
		mAV.addObject("lstTipoDoc", lstTipoDoc);
		mAV.addObject("preform", new PermisoPreFormModel());

		return mAV;
	}

	
	
	@PostMapping("/newpermiso")
	public ModelAndView create(@Valid @ModelAttribute("preform") PermisoPreFormModel preFormModel, BindingResult bindingResult) {
		ModelAndView mV;
		
		if(preFormModel.isEsDiario() == false && preFormModel.getDominio().isEmpty()) { //si se seleccionó permiso periodo y no se indicó patente
		    FieldError error = new FieldError("preform", "dominio", "Los permisos para períodos deben asignarse a rodados");
			bindingResult.addError(error);
		}
		
		if(bindingResult.hasErrors()) {
			
			mV = new ModelAndView(ViewRouteHelper.PERMISO_NEW);
			List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
			mV.addObject("lstTipoDoc", lstTipoDoc);
			
		} else {
			
			mV = new ModelAndView(ViewRouteHelper.PERMISO_FORMULARIO);
			// si no hay errores instancio un nuevo permiso de la clase hija correspondiente
			PermisoModel nuevoPermiso = null;
			if(preFormModel.isEsDiario()) {
				nuevoPermiso = new PermisoDiarioModel();
			}else {
				nuevoPermiso = new PermisoPeriodoModel();
				//si encuentra el rodado, se lo asigno al nuevo permiso, sino le asigno solo la patente
				RodadoModel rm = rodadoService.findByDominio(preFormModel.getDominio());

				if(rm!=null) {
					((PermisoPeriodoModel)nuevoPermiso).setRodado(rm);
				}else {
					((PermisoPeriodoModel)nuevoPermiso).getRodado().setDominio(preFormModel.getDominio().toUpperCase());
				}
			}
			
			//lo mismo para la persona: si la encuentro se la asigno, sino le completo el campo DNI			
			PersonaModel pm = personaService.findByTipoDocumentoAndDocumento(preFormModel.getTipoDocumento(), preFormModel.getDocumento());
			if(pm!=null) {
				nuevoPermiso.setPedido(pm);
			}else {
				nuevoPermiso.getPedido().setTipoDocumento(preFormModel.getTipoDocumento());
				nuevoPermiso.getPedido().setDocumento(preFormModel.getDocumento());
			}
			
			mV.addObject("permiso", nuevoPermiso);
			mV.addObject("esDiario", preFormModel.isEsDiario());
			mV.addObject("lstTipoDoc", Arrays.asList(TipoDocumento.values()));
			mV.addObject("lugares", lugarService.getAllOrderByLugar());
			
		}
		
		return mV;
	}
	
	
	@PostMapping("/postpermisodiario")
	public ModelAndView create(@Valid @ModelAttribute("permiso") PermisoDiarioModel permisoDiarioModel, BindingResult bindingResult,
							   @RequestParam(name="idDesde", required=true) String idDesdeStr,
							   @RequestParam(name="idHasta", required=true) String idHastaStr) {
		
		return insertPermiso(permisoDiarioModel, bindingResult, Integer.valueOf(idDesdeStr), Integer.valueOf(idHastaStr));
	}
	
	@PostMapping("/postpermisoperiodo")
	public ModelAndView create(@Valid @ModelAttribute("permiso") PermisoPeriodoModel permisoPeriodoModel, BindingResult bindingResult,
							   @RequestParam(name="idDesde", required=true) String idDesdeStr,
							   @RequestParam(name="idHasta", required=true) String idHastaStr) {
		
		return insertPermiso(permisoPeriodoModel, bindingResult, Integer.valueOf(idDesdeStr), Integer.valueOf(idHastaStr));
	}

	
	public ModelAndView insertPermiso(PermisoModel permisoModel, BindingResult bindingResult, int idDesde, int idHasta) {
		
		ModelAndView mV;		
		// si la fecha es anterior a hoy o despues de un año
		if ( permisoModel.getFecha().isBefore(LocalDate.now()) || permisoModel.getFecha().isAfter(LocalDate.now().plusYears(1)) ) {
			bindingResult.addError(new FieldError("permiso", "fecha", "La fecha es incorrecta"));
		}
		if(idDesde == idHasta) {
			bindingResult.addError(new FieldError("permiso", "desdeHasta", "El lugar de origen y destino no puede ser el mismo"));
		}
		if(bindingResult.hasErrors()) {
			mV = new ModelAndView(ViewRouteHelper.PERMISO_FORMULARIO);
			mV.addObject("esDiario", (permisoModel instanceof PermisoDiarioModel));
			mV.addObject("lstTipoDoc", Arrays.asList(TipoDocumento.values()));
			mV.addObject("lugares", lugarService.getAll());
			
		} else {
			mV = new ModelAndView(new RedirectView(ViewRouteHelper.PERMISO_SUCCESS_REDIRECT));
			
			permisoModel.agregarLugar(lugarService.findById(Integer.valueOf(idDesde)));
			permisoModel.agregarLugar(lugarService.findById(Integer.valueOf(idHasta)));
			
			PersonaModel pm = personaService.findById(permisoModel.getPedido().getId());
			if(pm==null) {
				pm = personaService.insertOrUpdate(permisoModel.getPedido());	
			}
			permisoModel.setPedido(pm);
						
			if(permisoModel instanceof PermisoPeriodoModel) {
				PermisoPeriodoModel ppm = (PermisoPeriodoModel) permisoModel;
				RodadoModel rm = rodadoService.findById(ppm.getRodado().getId());
				if(rm==null) {
					rm = rodadoService.insertOrUpdate(ppm.getRodado());
				}
				ppm.setRodado(rm);
			}
			permisoService.insertOrUpdate(permisoModel);
		}
		
		return mV;
	}
	
	
	@GetMapping("/success")
	public ModelAndView success() {
		return new ModelAndView(ViewRouteHelper.PERMISO_SUCCESS);
	}
	
	
	@GetMapping("/detalle/{id}")
	public ModelAndView showDetails(@PathVariable int id) {
		ModelAndView mV = new ModelAndView(ViewRouteHelper.PERMISO_DETAILS);
		PermisoModel pm = permisoService.findById(id);
		boolean activo = false;
		boolean esDiario = pm instanceof PermisoDiarioModel;
		if(esDiario && pm.getFecha().equals(LocalDate.now())) {
			activo = true;
		} else if(!esDiario) {
			PermisoPeriodoModel ppm = (PermisoPeriodoModel)pm;
			LocalDate fechaHasta = ppm.getFecha().plusDays(ppm.getCantDias());
			mV.addObject("fechaHasta", fechaHasta);
			if( ! (ppm.getFecha().isAfter(LocalDate.now()) || fechaHasta.isBefore(LocalDate.now())) ) {
				activo = true;
			}
		}
		mV.addObject("activo", activo);
		mV.addObject("esDiario", esDiario);
		mV.addObject("permiso", pm);
		return mV;
	}
	
	
	//GENERA EL QR
	@GetMapping("/getqr/{id}")
	public void crearQR(HttpServletResponse response, @PathVariable int id) 
	{
		
		try
		{
			String url = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/permiso/detalle/" + id;
			OutputStream outPutStream = response.getOutputStream();
			outPutStream.write(ZXingHelper.getQRCodeImage(url, 200, 200));
			outPutStream.flush();
			outPutStream.close();			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
















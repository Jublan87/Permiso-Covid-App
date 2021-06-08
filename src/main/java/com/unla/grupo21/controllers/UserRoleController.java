package com.unla.grupo21.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.servlet.view.RedirectView;

import com.lowagie.text.DocumentException;
import com.unla.grupo21.exporters.UserRolePDFExporter;
import com.unla.grupo21.helpers.ViewRouteHelper;
import com.unla.grupo21.models.UserRoleModel;
import com.unla.grupo21.services.IUserRoleService;
import com.unla.grupo21.services.IUserService;

@Controller
@RequestMapping("/userrole")
public class UserRoleController {
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_AUDITOR')")
	@GetMapping("")
	public ModelAndView index()
	{
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USERROLE_LIST);
		mAV.addObject("userRoles", userRoleService.getActivos());
		mAV.addObject("userRole", new UserRoleModel());
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/abm")
	public ModelAndView abm(@RequestParam(name="cambio", required=false) boolean cambio,
							HttpServletRequest request)
	{
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USERROLE_ABM);
		List<UserRoleModel> lstPerfiles;

		HttpSession session = request.getSession();
		
		if(cambio && session.getAttribute("userroles_inactivos_show") != null) {
			boolean valorActual = (boolean)session.getAttribute("userroles_inactivos_show");
			session.setAttribute("userroles_inactivos_show", !valorActual);
		} else if (session.getAttribute("userroles_inactivos_show") == null){
			session.setAttribute("userroles_inactivos_show", false);
		}
		
		if(session.getAttribute("userroles_inactivos_show") == null || (boolean)session.getAttribute("userroles_inactivos_show")) {
			lstPerfiles = userRoleService.getAll();
		} else {
			lstPerfiles = userRoleService.getActivos();
		}
		
		mAV.addObject("userRoles", lstPerfiles);
		mAV.addObject("abm", true);
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/new")
	public ModelAndView agregar()
	{
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USERROLE_NEW);
		mAV.addObject("userRole", new UserRoleModel());
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/newUserRole")
	public ModelAndView create(@Valid @ModelAttribute("userRole") UserRoleModel userRoleModel, BindingResult bindingResult)
	{
		ModelAndView mAV;
		
		if ( userRoleService.findByRole(userRoleModel.getRole()) != null) {
		    FieldError error = new FieldError("userRole", "role", "Ya existe un perfil con el nombre ingresado.");
			bindingResult.addError(error);
		}
		
		if(bindingResult.hasErrors()) {
			mAV = new ModelAndView(ViewRouteHelper.USERROLE_NEW);
		} else {
			mAV = new ModelAndView(new RedirectView(ViewRouteHelper.USERROLE_ABM_INDEX));
			userRoleService.insertOrUpdate(userRoleModel);
		}
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(name="id", required=true) int id)
	{
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USERROLE_EDIT);
		mAV.addObject("userRole", userRoleService.findById(id));
		mAV.addObject("edit", true);
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable int id)
	{
		ModelAndView mAV = new ModelAndView(new RedirectView(ViewRouteHelper.USERROLE_ABM_INDEX));
		long cantidadUsersAsignados = userService.countByUserActivoWhereRoleId(id);
		if(cantidadUsersAsignados == 0)
		{
			UserRoleModel urm = userRoleService.findById(id);
			urm.setActivo(!urm.isActivo());
			userRoleService.insertOrUpdate(urm);
			mAV.addObject("error", false);
			return mAV;
		}
		
		else
		{
			mAV.addObject("error", true);
			return mAV;
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/restore/{id}")
	public ModelAndView restore(@PathVariable int id)
	{
		ModelAndView mAV = new ModelAndView(new RedirectView(ViewRouteHelper.USERROLE_ABM_INDEX));
		UserRoleModel urm = userRoleService.findById(id);
		if(!urm.isActivo()) {
			urm.setActivo(true);
			userRoleService.insertOrUpdate(urm);
		}
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=perfiles_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<UserRoleModel> listUserRoles = userRoleService.getAll();
         
        UserRolePDFExporter exporter = new UserRolePDFExporter(listUserRoles);
        exporter.export(response);
    }
}



















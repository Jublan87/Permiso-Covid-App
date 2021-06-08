package com.unla.grupo21.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.unla.grupo21.exporters.UserPDFExporter;
import com.unla.grupo21.helpers.ViewRouteHelper;
import com.unla.grupo21.models.TipoDocumento;
import com.unla.grupo21.models.UserModel;
import com.unla.grupo21.models.UserRoleModel;
import com.unla.grupo21.services.IUserRoleService;
import com.unla.grupo21.services.IUserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;
	
    @Autowired
    PasswordEncoder passwordEncoder;
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_AUDITOR')")
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_LIST);
		mAV.addObject("users", userService.getActivos());
		return mAV;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/abm")
	public ModelAndView abm(@RequestParam(name="cambio", required=false) boolean cambio,
							HttpServletRequest request) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ABM);
		List<UserModel> lstUsuarios;
		
		HttpSession session = request.getSession();
		
		if(cambio && session.getAttribute("users_inactivos_show") != null) {
			boolean valorActual = (boolean)session.getAttribute("users_inactivos_show");
			session.setAttribute("users_inactivos_show", !valorActual);
		} else if (session.getAttribute("users_inactivos_show") == null){
			session.setAttribute("users_inactivos_show", false);
		}
		
		if(session.getAttribute("users_inactivos_show") == null || (boolean)session.getAttribute("users_inactivos_show")) {
			lstUsuarios = userService.getAll();
		} else {
			lstUsuarios = userService.getActivos();
		}
		mAV.addObject("users", lstUsuarios);
		mAV.addObject("abm", true);
		return mAV;
	}
	

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/new")
	public ModelAndView agregar() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_NEW);
		List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
		mAV.addObject("user", new UserModel());
		mAV.addObject("lstTipoDoc", lstTipoDoc);
		mAV.addObject("userroles", userRoleService.getActivos());
		return mAV;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/newuser")
	public ModelAndView create(@Valid @ModelAttribute("user") UserModel userModel, BindingResult bindingResult,
							   @RequestParam(name="idUserRole", required=true) String idUserRoleStr,
							   @RequestParam(name="edit", required=false, defaultValue="false") boolean edit,
							   @RequestParam(name="oldpassword", required=false, defaultValue="") String oldPassword) {
		ModelAndView mV;
		
		// si el nombre de usuario ya existe
		if ( userService.findUsernameAndFetchUserRoleEagerly(userModel.getUsername()) != null ) {
			// y no es un usuario que fue editado y tiene el mismo username que el ingresado
			if ( !(edit && userService.findById(userModel.getId()).getUsername().equals(userModel.getUsername())) ) {
			    FieldError error = new FieldError("user", "username", "El nombre de usuario ingresado ya existe.");
				bindingResult.addError(error);
			}
		}
		
		if(bindingResult.hasErrors()) {
			mV = new ModelAndView(ViewRouteHelper.USER_NEW);
			List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
			mV.addObject("lstTipoDoc", lstTipoDoc);
			mV.addObject("userroles", userRoleService.getActivos());
			mV.addObject("edit", edit);
		} else {
			mV = new ModelAndView(new RedirectView(ViewRouteHelper.USER_ABM_INDEX));
			int idUserRole = Integer.parseInt(idUserRoleStr);
			UserRoleModel urm = userRoleService.findById(idUserRole);
			userModel.setUserRole(urm);
			if(!edit || !oldPassword.equals(userModel.getPassword())) { //si es un usuario nuevo o la contraseña fue cambiada
				userModel.setPassword(passwordEncoder.encode(userModel.getPassword())); //la enctripto y la seteo al usuario
			}
			userService.insertOrUpdate(userModel);
		}
		
		return mV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(name="id", required=true) int id,
							 @RequestParam(name="reactivar", required=false, defaultValue="false") Boolean reactivar) {
		ModelAndView mV = new ModelAndView(ViewRouteHelper.USER_EDIT);
		UserModel user = userService.findById(id);
		if(reactivar) {
			user.setActivo(true);
			if(!user.getUserRole().isActivo()) { // si el usuario a reactivar tiene un userrole inactivo, seteo en null
				user.setUserRole(null);
			}
		}
		mV.addObject("user", user);
		List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
		mV.addObject("lstTipoDoc", lstTipoDoc);
		mV.addObject("userroles", userRoleService.getActivos());
		mV.addObject("edit", true);
		return mV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable int id) {
		RedirectView rV = new RedirectView(ViewRouteHelper.USER_ABM_INDEX);
		UserModel um = userService.findById(id);
		um.setActivo(false);
		userService.insertOrUpdate(um);
		return rV;
	}
	
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
    @GetMapping("/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=usuarios_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<UserModel> listUsers = userService.getActivos();
         
        UserPDFExporter exporter = new UserPDFExporter(listUsers);
        exporter.export(response);
         
    }
}

package com.unla.grupo21.helpers;

public class ViewRouteHelper {

	//***Views***
	
	////HOME
	public static final String INDEX = "home/index";
		
	////LOGGING
	public static final String LOGGING_LOGIN = "logging/login";
	public static final String LOGGING_LOGOUT = "logging/logout";
	
	////USER
	public static final String USER_LIST = "user/index";
	public static final String USER_ABM = "user/index";
	public static final String USER_NEW = "user/form";
	public static final String USER_EDIT = "user/form";
	
	////USERROLE
	public static final String USERROLE_LIST = "userrole/index";
	public static final String USERROLE_ABM = "userrole/index";
	public static final String USERROLE_NEW = "userrole/form";
	public static final String USERROLE_EDIT = "userrole/form";

	////PERMISO
	public static final String PERMISO_NEW = "permiso/form1";
	public static final String PERMISO_FORMULARIO = "permiso/form2";
	public static final String PERMISO_SUCCESS = "permiso/success";
	
	////PERMISO BUSQUEDA
	public static final String PERMISO_BUSCAR = "permiso/buscar/betweendates";
	public static final String PERMISO_BUSCAR_RODADO = "permiso/buscar/rodado";
	public static final String PERMISO_BUSCAR_PERSONA = "permiso/buscar/persona";
	public static final String PERMISO_RESULTS = "permiso/buscar/results";
	public static final String PERMISO_DETAILS = "permiso/details";
	
	//***Redirects***
	public static final String ROUTE_INDEX = "/index";
	public static final String USER_ABM_INDEX = "/user/abm";
	public static final String USERROLE_ABM_INDEX = "/userrole/abm";
	public static final String PERMISO_SUCCESS_REDIRECT = "/permiso/success";
}
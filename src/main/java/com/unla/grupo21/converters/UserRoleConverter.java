package com.unla.grupo21.converters;

import org.springframework.stereotype.Component;

import com.unla.grupo21.entities.UserRole;
import com.unla.grupo21.models.UserRoleModel;

@Component("userRoleConverter")
public class UserRoleConverter {

	public UserRoleModel entityToModel(UserRole userRole)
	{
		return new UserRoleModel(userRole.getId(), userRole.getRole(), userRole.isActivo());
	}
	
	public UserRole modelToEntity(UserRoleModel userRoleModel)
	{
		return new UserRole(userRoleModel.getId(), userRoleModel.getRole(), userRoleModel.isActivo());
	}
}

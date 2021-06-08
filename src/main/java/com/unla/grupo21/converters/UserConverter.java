package com.unla.grupo21.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.grupo21.entities.User;
import com.unla.grupo21.models.UserModel;

@Component("userConverter")
public class UserConverter {

	@Autowired
	@Qualifier("userRoleConverter")
	private UserRoleConverter userRoleConverter;
	
	public UserModel entityToModel(User user) {
		return new UserModel(user.getId(), user.getNombre(), user.getApellido(), user.getTipoDocumento(), user.getDocumento(), user.getEmail(),
				user.getUsername(), user.getPassword(), userRoleConverter.entityToModel(user.getUserRole()), user.isActivo());
	}
	
	public User modelToEntity(UserModel userModel) {
		return new User(userModel.getId(), userModel.getNombre(), userModel.getApellido(), userModel.getTipoDocumento(), userModel.getDocumento(),
				userModel.getEmail(), userModel.getUsername(), userModel.getPassword(), userRoleConverter.modelToEntity(userModel.getUserRole()), userModel.isActivo());
	}

}

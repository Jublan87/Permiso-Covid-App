package com.unla.grupo21.services;

import java.util.List;

import com.unla.grupo21.models.UserModel;

public interface IUserService {

	public UserModel findById(int id);
	
	public long countByActivoTrue();
	
	public long countByUserActivoWhereRoleId(int userRoleId);
	
	public UserModel findUsernameAndFetchUserRoleEagerly(String username);
	
	public List<UserModel> getActivos();
	
	public List<UserModel> getAll();
	
	public UserModel insertOrUpdate(UserModel userModel);
	
	public boolean remove(int id);
	
}

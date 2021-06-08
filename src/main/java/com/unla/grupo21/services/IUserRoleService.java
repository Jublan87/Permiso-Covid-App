package com.unla.grupo21.services;

import java.util.List;

import com.unla.grupo21.models.UserRoleModel;

public interface IUserRoleService {

	public UserRoleModel findById(int id);
	public List<UserRoleModel> getActivos();
	public long count();
	public long countByActivoTrue();
	public UserRoleModel findByRole(String role);
	public List<UserRoleModel> getAll();
	public UserRoleModel insertOrUpdate(UserRoleModel userRoleModel);
	public boolean remove(int id);
}

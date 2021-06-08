package com.unla.grupo21.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo21.converters.UserRoleConverter;
import com.unla.grupo21.entities.UserRole;
import com.unla.grupo21.models.UserRoleModel;
import com.unla.grupo21.repositories.IUserRoleRepository;
import com.unla.grupo21.services.IUserRoleService;

@Service("userRoleService")
public class UserRoleService implements IUserRoleService{

	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;
	
	@Autowired
	@Qualifier("userRoleConverter")
	private UserRoleConverter userRoleConverter;
	
	@Override
	public UserRoleModel findById(int id) {
		return userRoleConverter.entityToModel(userRoleRepository.findById(id));
	}
	
	@Override
	public long count() {
		return userRoleRepository.count();
	}
	
	@Override
	public long countByActivoTrue() {
		return userRoleRepository.countByActivoTrue();
	}
	
	@Override
	public UserRoleModel findByRole(String role) {
		UserRoleModel urm = null;
		UserRole ur = userRoleRepository.findByRole(role);
		if(ur != null) {
			urm = userRoleConverter.entityToModel(ur);
		}
		return urm;
	}
	
	@Override
	public List<UserRoleModel> getActivos() {
		List<UserRoleModel> lstUserRoleModel = new ArrayList<UserRoleModel>();
		for(UserRole ur : userRoleRepository.findByActivoTrue()) {
			lstUserRoleModel.add(userRoleConverter.entityToModel(ur));
		}
		return lstUserRoleModel;
	}
	
	@Override
	public List<UserRoleModel> getAll() {
		List<UserRoleModel> lstUserRoleModel = new ArrayList<UserRoleModel>();
		for(UserRole u : userRoleRepository.findAll()) {
			lstUserRoleModel.add(userRoleConverter.entityToModel(u));
		}
		return lstUserRoleModel;
	}
	
	@Override
	public UserRoleModel insertOrUpdate(UserRoleModel userRoleModel) {
	
		UserRole userRole = userRoleRepository.save(userRoleConverter.modelToEntity(userRoleModel));
		return userRoleConverter.entityToModel(userRole);
	}
	
	@Override
	public boolean remove(int id) {
	
		try
		{
			userRoleRepository.deleteById(id);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	
}

package com.unla.grupo21.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.grupo21.converters.UserConverter;
import com.unla.grupo21.entities.UserRole;
import com.unla.grupo21.models.UserModel;
import com.unla.grupo21.repositories.IUserRepository;
import com.unla.grupo21.services.IUserService;

@Service("userService")
public class UserService implements UserDetailsService, IUserService {
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.unla.grupo21.entities.User user = userRepository.findUsernameAndFetchUserRoleEagerly(username);
		return buildUser(user, buildGrantedAuthorities(user.getUserRole()));
	}
	
	private User buildUser(com.unla.grupo21.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isActivo(),
						true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked
						grantedAuthorities);
	}
	
	private List<GrantedAuthority> buildGrantedAuthorities(UserRole userRole) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}
	
	@Override
	public UserModel findById(int id) {
		UserModel um = null;
		com.unla.grupo21.entities.User u = userRepository.findById(id);
		if(u != null) {
			um = userConverter.entityToModel(u);
		}
		return um;
	}
	

	@Override
	public long countByActivoTrue() {
		return userRepository.countByActivoTrue();
	}

	@Override
	public long countByUserActivoWhereRoleId(int userRoleId) {
		return userRepository.countByUserActivoWhereRoleId(userRoleId);
	}
	
	@Override
	public UserModel findUsernameAndFetchUserRoleEagerly(String username) {
		UserModel um = null;
		com.unla.grupo21.entities.User u = userRepository.findUsernameAndFetchUserRoleEagerly(username);
		if(u != null) {
			um = userConverter.entityToModel(u);
		}
		return um;
	}
	

	@Override
	public List<UserModel> getActivos() {
		List<UserModel> lstUserModel = new ArrayList<UserModel>();
		for(com.unla.grupo21.entities.User u : userRepository.findByActivoTrue()) {
			lstUserModel.add(userConverter.entityToModel(u));
		}
		return lstUserModel;
	}
	
	
	@Override
	public List<UserModel> getAll() {
		List<UserModel> lstUserModel = new ArrayList<UserModel>();
		for(com.unla.grupo21.entities.User u : userRepository.findAll()) {
			lstUserModel.add(userConverter.entityToModel(u));
		}
		return lstUserModel;
	}

	@Override
	public UserModel insertOrUpdate(UserModel userModel) {
		com.unla.grupo21.entities.User user = userRepository.save(userConverter.modelToEntity(userModel));
		return userConverter.entityToModel(user);
	}
	
	@Override
	public boolean remove(int id) {
		try {
			userRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}

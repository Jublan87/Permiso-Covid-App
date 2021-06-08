package com.unla.grupo21.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo21.entities.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable> {
	
	public abstract User findById(int id);
	
	public abstract List<User> findByActivoTrue();
	
	public abstract long countByActivoTrue();
	
	@Query("SELECT COUNT(u) FROM User u WHERE u.activo = true and u.userRole.id = :userRoleId")
	public abstract long countByUserActivoWhereRoleId(@Param("userRoleId") int userRoleId);
	
	@Query("SELECT u FROM User u JOIN FETCH u.userRole WHERE u.username = (:username)")
	public abstract User findUsernameAndFetchUserRoleEagerly(@Param("username") String username);
	

}
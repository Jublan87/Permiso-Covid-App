package com.unla.grupo21.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo21.entities.UserRole;

@Repository("userRoleRepository")
public interface IUserRoleRepository extends JpaRepository<UserRole, Serializable>{

	public abstract UserRole findById(int id);
	
	public abstract List<UserRole> findByActivoTrue();
	
	public abstract long countByActivoTrue();
	
	public abstract long count();
	
	@Query("SELECT r FROM UserRole r WHERE r.role = (:role)")
	public abstract UserRole findByRole(@Param("role") String role);
}

package io.abhi.hotelmanagement.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import io.abhi.hotelmanagement.entity.RoleEntity;

public interface RolesRepo extends JpaRepository<RoleEntity, Long>{


	public RoleEntity findByRolename(String role);

		
}

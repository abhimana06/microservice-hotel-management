package io.abhi.hotelmanagement.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.abhi.hotelmanagement.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long>{
	
	Optional<UserEntity> findById(long id);
	
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);


}

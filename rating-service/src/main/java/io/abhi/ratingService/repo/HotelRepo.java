package io.abhi.ratingService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.abhi.ratingService.entity.HotelEntity;

@Repository
public interface HotelRepo extends JpaRepository<HotelEntity, Long>{
	
	

}

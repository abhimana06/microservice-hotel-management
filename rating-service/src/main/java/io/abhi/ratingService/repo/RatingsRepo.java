package io.abhi.ratingService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.abhi.ratingService.entity.HotelInfoEntity;
import io.abhi.ratingService.entity.RatingsEntity;

@Repository
public interface RatingsRepo extends JpaRepository<RatingsEntity, Long>{
	
	

}

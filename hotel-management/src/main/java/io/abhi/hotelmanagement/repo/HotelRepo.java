package io.abhi.hotelmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.abhi.hotelmanagement.entity.HotelEntity;

@Repository
public interface HotelRepo extends JpaRepository<HotelEntity, Long>{
	
	

}

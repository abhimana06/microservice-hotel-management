package io.abhi.hotelinfoservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.abhi.hotelinfoservice.entity.HotelEntity;

@Repository
public interface HotelRepo extends JpaRepository<HotelEntity, Long>{
	
	

}

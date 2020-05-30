package io.abhi.hotelinfoservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.abhi.hotelinfoservice.entity.HotelInfoEntity;

@Repository
public interface HotelInfoRepo extends JpaRepository<HotelInfoEntity, Long>{
	
	

}

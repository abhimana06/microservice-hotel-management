package io.abhi.hotelmanagement.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Repository;


import io.abhi.hotelmanagement.entity.HotelTypeEntity;

@Repository
@org.springframework.transaction.annotation.Transactional
public interface HotelTypeRepo extends JpaRepository<HotelTypeEntity, Long>{

	Optional<HotelTypeEntity> findByTypeCode(String typeCode);

	
}

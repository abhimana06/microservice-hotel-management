package io.abhi.hotelmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.abhi.hotelmanagement.entity.TokenEntity;
import io.abhi.hotelmanagement.enumeration.TokenStatus;

@Repository
@Transactional
public interface TokenRepo extends JpaRepository<TokenEntity, Long>{

	TokenEntity findByAccessToken(String token);
	
	@Query("Select c from TokenEntity c where c.accessToken=:plainToken and c.tokenStatus=:active and c.isActive=true ")
	TokenEntity checkToken(@Param("plainToken") String plainToken,@Param("active") TokenStatus active);


	@Modifying
	@Query(" Update TokenEntity c set c.isActive=:isActive,tokenStatus=:tokenStatus where c.accessToken=:accessToken")
	int changeTokenStatus(@Param("accessToken") String accessToken, @Param("isActive") Boolean isActive, @Param("tokenStatus") TokenStatus tokenStatus );


}

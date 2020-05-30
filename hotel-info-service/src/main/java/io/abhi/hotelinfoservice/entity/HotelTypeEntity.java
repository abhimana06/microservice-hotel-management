package io.abhi.hotelinfoservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "HOTEL_TYPE")
@Data
public class HotelTypeEntity {
	
	//dining , cuisine, stay
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "HOTEL_TYPE")
	private String hotelType;
	
	@Column(name = "TYPE_CODE")
	private String typeCode;
}

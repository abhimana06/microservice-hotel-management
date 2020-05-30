package io.abhi.hotelmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "HOTEL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelEntity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4677571941078488454L;


	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long hotelId;
	
	
	@Column(name="HOTEL_TITLE")
	private  String title;
	
	@Column(name="URL")
	private  String url;
	
	@Column(name="ADDRESS")
	private  String address;
	
	@Column(name="OUTLET_NUMBER")
	private  long outletNumber;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private HotelTypeEntity type;
	
	
	@Column(name="IMAGE_URL")
	private String imageUrl;
	
	
}

package io.abhi.hotelmanagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import io.abhi.hotelmanagement.enumeration.TokenStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="TOKEN")
@Getter
@Setter
public class TokenEntity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6908707080508733373L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name="USER_ID",nullable=false)
	private UserEntity userId;
	
	@Column(name="USERNAME",length=400)
	private String username;
	
	@Column(name="ACCESS_TOKEN",length=1000)
	private String accessToken;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private TokenStatus tokenStatus;
	
	@Column(name="TOKEN_EXPIRY")
	@Temporal(TemporalType.TIME)
	private Date tokenExpiry;
	
	
	

}
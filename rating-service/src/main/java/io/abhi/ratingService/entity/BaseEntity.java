package io.abhi.ratingService.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -569008090074841155L;
	
	@Column(name="CREATED_TIME")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected  Date createdTime =  new Date();
	
	@Column(name="MODIFIED_TIME")
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected  Date ModifiedTime;
	
	@Column(name="CREATED_BY")
	@CreatedBy
	protected  String createdBy;
	
	@Column(name="MODIFIED_BY")
	@LastModifiedBy
	protected  String ModifiedBy;
	
	@Column(name = "isACTIVE", columnDefinition = "TINYINT")
	protected boolean isActive = Boolean.TRUE;;
	
	
	
	
	
}

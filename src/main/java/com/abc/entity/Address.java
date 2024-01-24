/**
 * 
 */
package com.abc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Entity(name = "address")
@Setter
@Getter
//@JsonSerialize(using = AddressSerializer.class)
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long addressId;
	
	private String city;
	
	
	
}

package com.abc.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity(name="student")
@Data
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String name;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id",referencedColumnName = "id")
	private Address address;
	@JsonManagedReference
	@OneToMany(mappedBy = "studentId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Subject> subjects;
	
}

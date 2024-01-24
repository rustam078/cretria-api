package com.abc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.abc.entity.Student;


public interface StudentRepository extends JpaRepository<Student, Long>,JpaSpecificationExecutor<Student>{

	Student findByName(String name);
	
	List<Student> findByAddressCity(String city);
	
	List<Student> findBySubjectsName(String name);
}

package com.abc.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.dto.RequestDto;
import com.abc.entity.Address;
import com.abc.entity.Student;
import com.abc.repo.AddressRepository;
import com.abc.repo.StudentRepository;
import com.abc.service.SpecificationService;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@RestController
public class CriteriaFilterController {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private SpecificationService<Student> specificationService;
	
	@GetMapping("/std/{name}")
	public Student getStudentByName(@PathVariable String name) {
		 Student byName = studentRepository.findByName(name);
		 System.out.println(byName);
		 return byName;
	}
	@GetMapping("/std/city/{name}")
	public List<Student> getStudentByCityName(@PathVariable String name) {
		return studentRepository.findByAddressCity(name);
	}
	@GetMapping("/std/subject/{name}")
	public List<Student> getStudentBySubjectName(@PathVariable String name) {
		return studentRepository.findBySubjectsName(name);
	}
	
	@PostMapping("/save")
	public Student saveStudent(@RequestBody Student std) {
		 Address savedAddress = addressRepository.save(std.getAddress());
	        std.setAddress(savedAddress);
	     return   studentRepository.save(std);
	}
	
	
	@PostMapping("/spec")
	public List<Student> getStudentsbyName(){
		
		Specification<Student> specification = new Specification<Student>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
				return criteriaBuilder.equal(root.get("name"), "Rustam ali");
			}
		};
		List<Student> all=  studentRepository.findAll(specification);
		return all;
	}
	
	@PostMapping("/specfication")
	public List<Student> getStudentsSpecbyName(@RequestBody RequestDto requestDto){
		Specification<Student> specificationByName = specificationService.getSpecificationByName(requestDto.getSearchRequestDto(),requestDto.getOperator());
		List<Student> all=  studentRepository.findAll(specificationByName);
		return all;
	}
}

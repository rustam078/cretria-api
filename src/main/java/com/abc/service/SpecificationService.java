package com.abc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.abc.dto.GlobalOperator;
import com.abc.dto.SearchRequestDto;
import com.abc.entity.Student;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class SpecificationService<T> {

	
	public Specification<Student> getSpecificationByName(SearchRequestDto srd){
		Specification<Student> specification = new Specification<Student>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
				return criteriaBuilder.equal(root.get(srd.getColumn()), srd.getValue());
			}
		};
		
		return specification;
	}
	
	public Specification<T> getSpecificationByName(List<SearchRequestDto> srd,GlobalOperator operator){
	
		List<Predicate> predicates= new ArrayList<>();
		return (root,query,criteriaBuilder)->{
			for(SearchRequestDto searchRequestDto:srd) {
				switch (searchRequestDto.getOperation()) {
				case EQUAL: {
					Predicate equal = criteriaBuilder.equal(root.get(searchRequestDto.getColumn()),searchRequestDto.getValue());
					predicates.add(equal);	
					break;
				}
				case LIKE: {
					Predicate like = criteriaBuilder.like(root.get(searchRequestDto.getColumn()),"%"+searchRequestDto.getValue()+"%");
					predicates.add(like);	
					
					break;
				}
				case IN: {
					String value = searchRequestDto.getValue();
					String[] split = value.split(",");
					  Predicate in = root.get(searchRequestDto.getColumn()).in(Arrays.asList(split));
					  predicates.add(in);
					break;
				}
				case GREATER_THAN: {
					Predicate greaterThan = criteriaBuilder.greaterThan(root.get(searchRequestDto.getColumn()),searchRequestDto.getValue());
					predicates.add(greaterThan);	
					break;
				}
				case LESS_THAN: {
					Predicate lessThan = criteriaBuilder.lessThan(root.get(searchRequestDto.getColumn()),searchRequestDto.getValue());
					predicates.add(lessThan);
					break;
				}
				case BETWEEN: {
					String value = searchRequestDto.getValue();
					String[] split = value.split(",");
					Predicate between = criteriaBuilder.between(root.get(searchRequestDto.getColumn()),Long.parseLong(split[0]),Long.parseLong(split[1]));
					predicates.add(between);
					break;
				}
				case JOIN: {
					Predicate join =criteriaBuilder.equal(root.join(searchRequestDto.getJoinTable()).get(searchRequestDto.getColumn()),searchRequestDto.getValue());
					predicates.add(join);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + searchRequestDto.getOperation());
				}
			}
			if(GlobalOperator.AND.equals(operator))
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			else
				return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
		};	
	}
	
	
}

package com.abc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entity.Address;


public interface AddressRepository extends JpaRepository<Address, Long>{

}

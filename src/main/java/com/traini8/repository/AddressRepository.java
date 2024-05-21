package com.traini8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traini8.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}

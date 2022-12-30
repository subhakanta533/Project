package com.HR.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HR.entity.HumanResource;
@Repository
public interface HRRepositary extends JpaRepository<HumanResource, Integer>{
	
 public List<HumanResource> findByEmailAndPassword(String email,String password);
	
	
	
	
}

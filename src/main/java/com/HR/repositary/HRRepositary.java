package com.HR.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HR.entity.HumanResource;
@Repository
public interface HRRepositary extends JpaRepository<HumanResource, Integer>{

}

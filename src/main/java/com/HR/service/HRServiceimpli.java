package com.HR.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HR.entity.HumanResource;
import com.HR.repositary.HRRepositary;

@Service("service")
public class HRServiceimpli implements HRService{

@Autowired
private HRRepositary repositary;

@Override
public HumanResource registerHR(HumanResource hr) {
HumanResource humanResource=repositary.save(hr);
return humanResource;
}




	





}


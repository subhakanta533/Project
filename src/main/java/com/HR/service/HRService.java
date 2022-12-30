package com.HR.service;

import java.util.HashMap;
import java.util.List;

import com.HR.entity.HumanResource;

public interface HRService {

	public HumanResource registerHR(HumanResource hr);
	
	public HashMap<String, Object> loginHumanResources(String email,String password);
	
	
	
}

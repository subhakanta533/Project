package com.HR.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.HR.entity.HumanResource;
import com.HR.repositary.HRRepositary;

@Service("service")
public class HRServiceimpli implements HRService{

@Autowired
private HRRepositary repositary;
@Value("${jwt.token}")
private String token;

@Override
public HumanResource registerHR(HumanResource hr) {
HumanResource humanResource=repositary.save(hr);
return humanResource;
}



@Override
public HashMap<String, Object> loginHumanResources(String email, String password) {
	List<HumanResource> humanResources=repositary.findByEmailAndPassword(email, password);
	System.out.println(humanResources);
	if (!humanResources.isEmpty()) {
	HumanResource hResource=	humanResources.get(0);
	HashMap<String, Object> hMap = new HashMap<String, Object>();
	hMap.put("userId",hResource.getUserId());
	hMap.put("token", token);
	return hMap;
	}
	
	
	return null;
}


}







	







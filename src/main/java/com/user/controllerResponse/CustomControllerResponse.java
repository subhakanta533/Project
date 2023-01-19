package com.user.controllerResponse;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.user.entity.UserEntity;
@Component
public class CustomControllerResponse {
	
	public HashMap<String, Object> loginResponseMap(UserEntity userentity) {
		if (userentity != null) {
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("userId", userentity.getUserId());
			hMap.put("name", userentity.getName());
			hMap.put("email", userentity.getEmail());
			hMap.put("dob", userentity.getDob());
			return hMap;
		}
		return null;
	}
		
		
		
		
	}
	
	
	
	



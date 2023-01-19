package com.user.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class JsonMessages {
	
	public HashMap<String, Object> constructResponseToken(Object response,String token) {
		HashMap<String, Object> responseMap = new HashMap<>();
		responseMap.put("result", response);
		if (token !=null) {
			responseMap.put("token",token);	
		}
		return responseMap;
	}
	
	public HashMap<String, Object> constructResponse(Object response) {
		HashMap<String, Object> responseMap = new HashMap<>();
		responseMap.put("result", response);	
		return responseMap;
	}
		
	
	public HashMap<String, Object> constructException(List<String> errorMessages) {
		HashMap<String, Object> responseMap = new HashMap<>();
		
		List<HashMap<String, String>> errorList = new ArrayList<HashMap<String,String>>();
		for ( String message : errorMessages) {
			HashMap<String, String> errorMap = new HashMap<>();
			errorMap.put("error", message);
			errorList.add(errorMap);	
		}
		responseMap.put("message", errorList);
		return responseMap;
	}
	public HashMap<String, Object> errorResponse(String message) {
		HashMap<String, Object> responseMap = new HashMap<>();
		responseMap.put("messages", message);
		return responseMap;
	}
	public HashMap<String, String> resultResponse(String message) {
		HashMap<String, String> responseMap = new HashMap<>();
		responseMap.put("messages", message);
		return responseMap;
	}
}

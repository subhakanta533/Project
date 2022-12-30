package com.HR.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HR.entity.HumanResource;
import com.HR.service.HRService;

@RestController
public class HRController {
	@Autowired
	private HRService iService;
	
	@PostMapping("/HR/save")
	
	
	public ResponseEntity<?>SaveHR(@RequestParam HumanResource humanresource){
		System.out.println(humanresource.getName());
		HumanResource humanResource2=iService.registerHR(humanresource);
		HashMap<String, Object> map = new HashMap<>();
		map.put("token", humanResource2.getUserName());
		map.put("userId", humanResource2.getUserId());
		
		
		return new ResponseEntity<>(map, HttpStatus.CREATED);
		
		
		
		
		
	}

	
	@GetMapping("/HR/save/login/{email}/{password}")
	public ResponseEntity<?> loginbyHR(@PathVariable String email,@PathVariable String password){
	HashMap<String, Object> hashMap = iService.loginHumanResources(email, password);
	
	if(hashMap !=null) {
        return new ResponseEntity<>(hashMap,HttpStatus.OK);
	}
	return new ResponseEntity<>("User Does not Exist",HttpStatus.FORBIDDEN);

	
}
}
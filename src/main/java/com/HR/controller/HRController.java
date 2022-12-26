package com.HR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.HR.entity.HumanResource;
import com.HR.service.HRService;

import jakarta.validation.Valid;

@RestController
public class HRController {
	@Autowired
	private HRService iService;
	
	@PostMapping("/HR/save")
	public ResponseEntity<HumanResource>SaveHR(@RequestBody HumanResource humanresource){
		HumanResource humanResource2=iService.registerHR(humanresource);
		return new ResponseEntity<HumanResource>(humanResource2, HttpStatus.CREATED);
		
		
		
		
		
	}
	
	
	
	
	

}

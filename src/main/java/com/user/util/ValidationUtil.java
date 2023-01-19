package com.user.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.user.configuration.JsonMessages;
import com.user.entity.UserEntity;
import com.user.model.UserModel;
import com.user.service.UserService;
@Component
public class ValidationUtil {

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private JsonMessages jsonMessages;
	
	public List<String>  validateNewUser(UserModel userModel) {
		List<String> errorMessageList = new ArrayList<String>();
		String email = userModel.getEmail();
		UserEntity user = userService.findByEmail(email);
		if (user != null) {
			errorMessageList.add("Email is already registered, Email: " + email);
		}
		
		LocalDate datenow = LocalDate.now();
		LocalDate dob = userModel.getDob();
		Period age = Period.between(dob, datenow);
		int year = age.getYears();
		if (year < 18) {
			errorMessageList.add("User must be atleast 18 years old");
		}
		
		long phoneno = userModel.getPhoneNo();
		UserEntity user2 = userService.findByPhoneNo(phoneno);
		if (user2 != null) {
			errorMessageList.add("Number has been already registered,Try new one");
		}
		return errorMessageList;
	}
}

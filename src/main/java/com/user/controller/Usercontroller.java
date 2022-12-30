package com.user.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.service.UserService;

@RestController
@RequestMapping("/userManagement")
public class Usercontroller {
	@Autowired
	private UserService userService;

	@PostMapping("/registerNewUser")
	public ResponseEntity<?> registerNewUser(@RequestBody User user) {
		User users = userService.registerUser(user);
		HashMap<String, Object> map = new HashMap<>();
		map.put("token", users.getUserName());
		map.put("userId", users.getUserId());

		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@GetMapping("/loginUser/{email}/{password}")
	public ResponseEntity<?> loginUser(@PathVariable String email, @PathVariable String password) {
		HashMap<String, Object> hashMap = userService.loginUser(email, password);

		if (hashMap != null) {
			return new ResponseEntity<>(hashMap, HttpStatus.OK);
		}
		return new ResponseEntity<>("User Does not Exist", HttpStatus.FORBIDDEN);

	}
}
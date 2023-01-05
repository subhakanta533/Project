package com.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.service.UserService;

@RestController
@RequestMapping("/userManagement")
public class UserController {
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

	@GetMapping("/allusers")
	public ResponseEntity<?> GetAllUsers() {
		List<User> users = userService.fetchallusers();
		if (users == null) {
			return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	/*
	 * @GetMapping("/findusers") List<User>fetchusers(@RequestParam(required =
	 * false,defaultValue = "")Optional<Integer> userId,
	 * 
	 * @RequestParam(required = false,defaultValue = "")String email,
	 * 
	 * @RequestParam(required = false,defaultValue = "")String name){
	 * //List<User>fetchusers(@RequestParam()int userId){ //return
	 * userService.findbynameandemail(name, email);
	 * 
	 * //return repositary.findByNameAndEmailAndUserId(name,email,userId);
	 * 
	 * if (userId==null&email==null) { List<User> list =
	 * userService.findbyname(name); return list; }
	 * 
	 * // return userService.findbyname(name);
	 * 
	 * if (userId==null&name==null) { List<User> list =
	 * userService.findbyemail(email); return list; }
	 * 
	 * return null;
	 * 
	 * }
	 */

	@GetMapping("/findbyemail/{email}")
	public ResponseEntity<?> FetchUserByEmail(@PathVariable String email) {
		return new ResponseEntity<>(userService.findbyemail(email), HttpStatus.OK);
	}

	@GetMapping("/findbyname/{name}")
	public ResponseEntity<?> FetchUserByName(@PathVariable String name) {
		return new ResponseEntity<>(userService.findbyname(name), HttpStatus.OK);
	}

	@GetMapping("/findbyuserid/{userId}")
	public ResponseEntity<?> FetchUserByUserId(@PathVariable Integer userId) {
		return new ResponseEntity<>(userService.findbyuserid(userId), HttpStatus.OK);
	}

}
package com.user.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user.configuration.JsonMessages;
import com.user.controllerResponse.CustomControllerResponse;
import com.user.entity.UserEntity;
import com.user.model.UserLoginModel;
import com.user.model.UserModel;
import com.user.service.UserService;
import com.user.util.JwtTokenUtil;
import com.user.util.ValidationUtil;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/userManagement")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwt;

	@Autowired
	private JsonMessages jsonMessages;

	@Autowired
	private ValidationUtil validationUtil;
	
	@Autowired
	private CustomControllerResponse  customResponse;

	@PostMapping("/registerNewUser")
	public ResponseEntity<?> registerNewUser(@RequestBody UserModel userModel) {
		List<String> errorMessageList = validationUtil.validateNewUser(userModel);
		if (errorMessageList != null && !errorMessageList.isEmpty()) {
			return new ResponseEntity<>(jsonMessages.constructException(errorMessageList), HttpStatus.BAD_REQUEST);
		}
		UserEntity users = userService.registerUser(userModel);
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", users.getUserId());
		map.put("name", users.getName());
		return new ResponseEntity<>(jsonMessages.constructResponse(map), HttpStatus.OK);

	}

	@PostMapping("/loginUser")
	public ResponseEntity<?> loginUser(@RequestBody UserLoginModel userLoginModel) {
		UserEntity userEntity = userService.loginUser(userLoginModel);
		HashMap<String, Object> loginResponseMap =customResponse.loginResponseMap(userEntity);
		String token = jwt.generateAccessToken(userEntity.getName(), userEntity.getRole(), userEntity.getUserId());
		if (loginResponseMap != null) {
			return new ResponseEntity<>(jsonMessages.constructResponseToken(loginResponseMap, token), HttpStatus.OK);
		}
		return new ResponseEntity<>("User Does not Exist", HttpStatus.FORBIDDEN);
	}

	@GetMapping("/allusers")
	public ResponseEntity<?> GetAllUsers(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");		
		String token = bearerToken.replaceAll("Bearer ","");
		System.out.println(token);
				
		try {
			boolean tokenStatus = jwt.isValidBearerToken(token);
			if (tokenStatus) {
				List<UserEntity> users = userService.fetchAllUsers();
				if (users == null) {
					return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<>(jsonMessages.constructResponse(users), HttpStatus.OK);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(jsonMessages.errorResponse("Session has been expired"), HttpStatus.FORBIDDEN);
	}

	@GetMapping("/findbyemail/{email}")
	public ResponseEntity<?> FetchUserByEmail(@PathVariable String email) {
		UserEntity user1 = userService.findByEmail(email);
		if (user1 == null) {
			return new ResponseEntity<>(jsonMessages.errorResponse("email id associated user is not found"),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(jsonMessages.constructResponse(user1), HttpStatus.OK);
	}

	@GetMapping("/findbyname/{name}")
	public ResponseEntity<?> FetchUserByName(@PathVariable String name) {
		UserEntity user2 = userService.findbByName(name);
		if (user2 == null) {
			return new ResponseEntity<>(jsonMessages.errorResponse("email id associated user is not found"),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(jsonMessages.constructResponse(user2), HttpStatus.OK);

	}

	@GetMapping("/findbyuserid/{userId}")
	public ResponseEntity<?> FetchUserByUserId(@PathVariable Integer userId) {
		UserEntity user3 = userService.findByUserId(userId);
		if (user3 == null) {
			return new ResponseEntity<>(jsonMessages.errorResponse("email id associated user is not found"),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(jsonMessages.constructResponse(user3), HttpStatus.OK);

	}

	@DeleteMapping("/deleteusers/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable int userId) {
		UserEntity user = userService.findByUserId(userId);
		if (user == null) {
			return new ResponseEntity<>(jsonMessages.errorResponse("email id associated user is not found"),
					HttpStatus.BAD_REQUEST);
		}
		String result = userService.deleteUserById(userId);
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@PutMapping("/update/{userId}/{name}/{phoneNo}/{role}/{address}")
	public ResponseEntity<?> Updatebyid(@PathVariable Integer userId,
			                            @PathVariable String name,
			                            @PathVariable long phoneNo,
			                            @PathVariable String role){
			                             
		
		UserEntity users = userService.findByUserId(userId);
		if (users !=null) {
		users.setName(name);
		users.setPhoneNo(phoneNo);
		users.setRole(role);
	
		UserEntity updatedResult=userService.saveUser(users);
		return new ResponseEntity<>(jsonMessages.resultResponse("Result has been updated"),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(jsonMessages.resultResponse("User is not available"),HttpStatus.BAD_REQUEST);
	}


	@PostMapping("/forgot-password/{email}")
	public ResponseEntity<?> forgotPassword(@PathVariable String email) {
		String response = userService.userforgotPassword(email);
		return new ResponseEntity<>(jsonMessages.constructResponseToken(null, response), HttpStatus.ACCEPTED);
	}

	@PutMapping("/reset-password/{token}/{email}/{password}")
	public ResponseEntity<?> resetPassword(@PathVariable String token, @PathVariable String email,
			@PathVariable String password) {
		String response = userService.userResetPassword(token, email, password);
		return new ResponseEntity<>(jsonMessages.resultResponse(response), HttpStatus.ACCEPTED);
	}

	@GetMapping("/loginJwt/{email}/{password}")
	public ResponseEntity<?> loginJwt(@PathVariable String email, @PathVariable String password) {
		return null;
	}

}

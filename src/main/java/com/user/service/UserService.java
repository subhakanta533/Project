package com.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.user.entity.User;

public interface UserService {

	public User registerUser(User hr);

	public HashMap<String, Object> loginUser(String email, String password);
	
	public List<User> fetchallusers();
	
	public List<User>findbyemail(String email);
	public List<User>findbyuserid(Integer userId);
	public List<User>findbyname(String name);
	public List<User>findbynameandemail(String name,String email);
	public List<User>findbyuseridandemail(Integer userId,String email);
	public List<User>findbyuseridandname(Integer userId,String name);
	public List<User>findbyuseridandnameandemail(String name,String email,Optional<Integer>userId);
	
	

}

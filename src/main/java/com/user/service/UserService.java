package com.user.service;

import java.util.HashMap;

import com.user.entity.User;

public interface UserService {

	public User registerUser(User hr);

	public HashMap<String, Object> loginUser(String email, String password);

}

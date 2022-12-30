package com.user.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.repositary.UserRepositary;

@Service("service")
public class UserServiceImpli implements UserService{

@Autowired
private UserRepositary repositary;

@Value("${jwt.token}")
private String token;

@Override
public User registerUser(User hr) {
User humanResource=repositary.save(hr);
return humanResource;
}



@Override
public HashMap<String, Object> loginUser(String email, String password) {
	List<User> user2=repositary.findByEmailAndPassword(email, password);
	System.out.println(user2);
	if (!user2.isEmpty()) {
	User User3=	user2.get(0);
	HashMap<String, Object> hMap = new HashMap<String, Object>();
	hMap.put("userId",User3.getUserId());
	hMap.put("token", token);
	return hMap;
	}
	
	
	return null;
}


}







	







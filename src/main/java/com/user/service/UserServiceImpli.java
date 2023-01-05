package com.user.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.repositary.UserRepositary;
import com.user.util.passwordAuthentication;

@Service("service")
public class UserServiceImpli implements UserService {

	@Autowired
	private UserRepositary repositary;

	@Value("${jwt.token}")
	private String token;

	@Autowired
	private passwordAuthentication pAuthentication;

	@Override
	public User registerUser(User user) {

		try {
			String encodedpass = pAuthentication.passwordhashing(user.getPassword());
			user.setPassword(encodedpass);
			// User user1=repositary.save(hr);

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		User user1 = repositary.save(user);
		return user1;
	}

	@Override
	public HashMap<String, Object> loginUser(String email, String password) {

		try {
			String encodedpass1 = pAuthentication.passwordhashing(password);
			List<User> user2 = repositary.findByEmailAndPassword(email, encodedpass1);

			if (!user2.isEmpty()) {
				User User3 = user2.get(0);
				HashMap<String, Object> hMap = new HashMap<String, Object>();
				hMap.put("userId", User3.getUserId());
				hMap.put("token", token);
				return hMap;
			}

			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<User> fetchallusers() {
		return repositary.findAll();
	}



	@Override
	public List<User> findbynameandemail(String name, String email) {
		return repositary.findByNameAndEmail(name, email);
	}

	@Override
	public List<User> findbyuseridandemail(Integer userId, String email) {
	return repositary.findByUserIdAndEmail(userId, email);
	}

	@Override
	public List<User> findbyuseridandname(Integer userId, String name) {
	return repositary.findByUserIdAndName(userId, name);
	}

	@Override
	public List<User> findbyemail(String email) {
		return repositary.findByEmail(email);
	}

	@Override
	public List<User> findbyuserid(Integer userId) {
	return repositary.findByUserId(userId);
	}

	@Override
	public List<User> findbyname(String name) {
		return repositary.findByName(name);
	}

	@Override
	public List<User> findbyuseridandnameandemail(String name, String email, Optional<Integer> userId) {
		return repositary.findByNameAndEmailAndUserId(name, email, userId);
	}

}

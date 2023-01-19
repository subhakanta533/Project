package com.user.service;

import java.util.List;
import com.user.entity.UserEntity;
import com.user.model.UserLoginModel;
import com.user.model.UserModel;

public interface UserService {
	public UserEntity saveUser(UserEntity userEntity);

	public UserEntity registerUser(UserModel userModel);

	public UserEntity loginUser(UserLoginModel userLoginModel);

	public List<UserEntity> fetchAllUsers();

	public UserEntity findByEmail(String email);

	public UserEntity findByUserId(Integer userId);

	public UserEntity findbByName(String name);

	public String deleteUserById(Integer userId);

	public String userforgotPassword(String email);
	
	public String userResetPassword(String token,String email, String password);

	public UserEntity findByPhoneNo(long phoneno);

}

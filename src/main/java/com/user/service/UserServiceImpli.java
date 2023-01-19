package com.user.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.entity.UserEntity;
import com.user.model.UserLoginModel;
import com.user.model.UserModel;
import com.user.repositary.UserRepositary;
import com.user.transformer.UserTransformer;
import com.user.util.JwtTokenUtil;
import com.user.util.PasswordAuthentication;

@Service("service")
public class UserServiceImpli implements UserService {

	@Autowired
	private UserRepositary repositary;

	@Autowired
	private PasswordAuthentication pAuthentication;

	@Autowired
	private JwtTokenUtil jwt;

	@Autowired
	private UserTransformer userTransformer;
	
	

	@Override
	public UserEntity registerUser(UserModel userModel) {
		UserEntity user = null;
		try {
			user = userTransformer.copyPropereties(userModel);
			String encodedpass = pAuthentication.passwordhashing(user.getPassword());
			user.setPassword(encodedpass);
			user = repositary.save(user);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return user;
	}
	

	@Override
	public UserEntity loginUser(UserLoginModel userLoginModel) {
		String encodedpass = "";
		try {
			encodedpass = pAuthentication.passwordhashing(userLoginModel.getPassword());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UserEntity userentity = repositary.findByEmailAndPassword(userLoginModel.getEmail(), encodedpass);
		return userentity != null ? userentity : null;
	}

	@Override
	public List<UserEntity> fetchAllUsers() {
		return repositary.findAll();
	}

	@Override
	public UserEntity findByEmail(String email) {
		return repositary.findByEmail(email);
	}

	@Override
	public UserEntity findByUserId(Integer userId) {
		return repositary.findByUserId(userId);
	}

	@Override
	public UserEntity findbByName(String name) {
		return repositary.findByName(name);
	}

	@Override
	public String deleteUserById(Integer userId) {
		repositary.deleteById(userId);

		return "user has been deleted";
	}

	
	@Override
	public String userforgotPassword(String email) {
		
         UserEntity userEntity=repositary.findByEmail(email);
		if (userEntity==null) {
			return "Invalid email id.";
		}
		else {
			String token=jwt.generateAccessToken(userEntity.getName(), userEntity.getRole(), userEntity.getUserId());
		return token;
		}

	}


	@Override
	public UserEntity findByPhoneNo(long phoneno) {
		return repositary.findByPhoneNo(phoneno);
	}

	
	@Override
	public String userResetPassword(String token,String email, String password) {
		
			boolean tokenStatus;
			try {
				tokenStatus = jwt.isValidBearerToken(token);
				if (tokenStatus) {
					UserEntity userEntity=repositary.findByEmail(email);
					String encodedPass=pAuthentication.passwordhashing(password);
					userEntity.setPassword(encodedPass);
					repositary.save(userEntity);
					return  "Password has been reseted";
			    } 
			}
				catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Token has been expired";
	
		}


	@Override
	public UserEntity saveUser(UserEntity userEntity) {
		return repositary.save(userEntity);
	}
		
}


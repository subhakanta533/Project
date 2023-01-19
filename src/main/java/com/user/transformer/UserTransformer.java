package com.user.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.user.entity.UserEntity;
import com.user.model.UserModel;

@Component
public class UserTransformer {

	public UserEntity copyPropereties(UserModel userModel) {
		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(userModel, user);
		return user;

	}

}
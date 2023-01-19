package com.user.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.UserEntity;

@Repository
public interface UserRepositary extends JpaRepository<UserEntity, Integer> {

	public UserEntity  findByPhoneNo(Long phoneNo);

	public UserEntity findByEmail(String email);

	public UserEntity findByUserId(Integer userId);

	public UserEntity findByName(String name);
	
	public UserEntity findByEmailAndPassword(String email, String password);

}

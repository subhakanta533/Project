package com.user.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.User;
@Repository
public interface UserRepositary extends JpaRepository<User, Integer>{
	
 public List<User> findByEmailAndPassword(String email,String password);
	
	
	
	
}

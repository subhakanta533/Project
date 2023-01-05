package com.user.repositary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.User;

@Repository
public interface UserRepositary extends JpaRepository<User, Integer> {

	public List<User> findByEmailAndPassword(String email, String password);

	public List<User> findByEmail(String email);

	public List<User> findByUserId(Integer userId);

	public List<User> findByName(String name);

	public List<User> findByUserIdAndName(Integer userId, String name);

	public List<User> findByUserIdAndEmail(Integer userId, String email);

	public List<User> findByNameAndEmail(String name, String email);
	
	public List<User> findByNameAndEmailAndUserId(String name ,String email,Optional<Integer> userId);

}

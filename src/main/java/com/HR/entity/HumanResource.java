package com.HR.entity;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "HR_DETAILS")
public class HumanResource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Userid;
	
	@Column
	private String Name;
	
	@Column
	private String Username;
	@Column
	private String Email;
	
	@Column
	private String Adress;
	@Column
	private Long Phoneno;
	@Column
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate Dob;
	
	@Column
	private String Password;
	
	@Column
	private String Role;

	public Integer getUserid() {
		return Userid;
	}

	public void setUserid(Integer userid) {
		Userid = userid;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAdress() {
		return Adress;
	}

	public void setAdress(String adress) {
		Adress = adress;
	}

	public Long getPhoneno() {
		return Phoneno;
	}

	public void setPhoneno(Long phoneno) {
		Phoneno = phoneno;
	}

	public LocalDate getDob() {
		return Dob;
	}

	public void setDob(LocalDate dob) {
		Dob = dob;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public HumanResource(Integer userid, String name, String username, String email, String adress, Long phoneno,
			LocalDate dob, String password, String role) {

		Userid = userid;
		Name = name;
		Username = username;
		Email = email;
		Adress = adress;
		Phoneno = phoneno;
		Dob = dob;
		Password = password;
		Role = role;
	}

	public HumanResource() {
	}
}

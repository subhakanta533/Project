package com.user.enums;

public enum Gender {
	MALE("male"), FEMALE("female"), OTHERS("others");

	public String name;

	private Gender(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

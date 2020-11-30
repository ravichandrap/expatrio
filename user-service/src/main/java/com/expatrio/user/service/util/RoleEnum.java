package com.expatrio.user.service.util;

public enum RoleEnum {

	ADMIN("ADMIN"), USER("USER");
	//add

	private final String role;

	RoleEnum(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
}

package com.expatrio.user.service.beans;

public enum  Role {
    USER("ROLE_User"),
    CUSTOMER("ROLE_Customer"),
    ADMIN("ROLE_Admin");
    private String value;

    Role(String role) {
        this.value = role;
    }

    public String getValue() {
        return value;
    }
}

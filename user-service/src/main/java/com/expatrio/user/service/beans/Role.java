package com.expatrio.user.service.beans;

public enum  Role {
    USER("User"),
    CUSTOMER("Customer"),
    ADMIN("Admin");
    private String value;

    Role(String role) {
        this.value = role;
    }

    public String getValue() {
        return value;
    }
}

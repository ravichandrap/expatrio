package com.expatrio.user.service.beans;

import com.expatrio.user.service.entities.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class RoleDetails {
    private Long id;
    private String name;

    public RoleDetails(){}
    public RoleDetails(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public  RoleDetails(Role role) {
        this.name = role.getName();
        this.id = role.getId();
    }

    public  RoleDetails(Long id) {
        this.id = id;
    }

    public  Role of(){
        return new Role(this.getId(), this.getName());
    }
    public  Role of(RoleDetails roleDetails){
        return new Role(roleDetails.getId(), roleDetails.getName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

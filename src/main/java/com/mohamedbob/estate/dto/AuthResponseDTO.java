package com.mohamedbob.estate.dto;

import java.util.List;

public class AuthResponseDTO {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String email;
    private String name;
    private List<String> roles;

    public AuthResponseDTO(Long id, String token, String email, String name, List<String> roles) {
        this.id = id;
        this.token = token;
        this.email = email;
        this.name = name;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

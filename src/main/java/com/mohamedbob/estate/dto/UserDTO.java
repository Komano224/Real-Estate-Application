package com.mohamedbob.estate.dto;

import com.mohamedbob.estate.model.Favorite;
import com.mohamedbob.estate.model.Inquiry;
import com.mohamedbob.estate.model.Property;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    private String password;

    private List<Property>properties = new ArrayList<>();

    private List<Favorite> favorites = new ArrayList<>();

    private List<Inquiry> inquiries = new ArrayList<>();

    private Set<String> roles = new HashSet<>();

    private List<ChatMessageDTO> chatMessages = new ArrayList<>();

    public UserDTO() {

    }
    public UserDTO(Long id, String name, String phoneNumber, String email, String password,
                   List<Property> properties, List<Favorite> favorites, List<Inquiry> inquiries, Set<String> roles, List<ChatMessageDTO> chatMessages) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.properties = properties;
        this.favorites = favorites;
        this.inquiries = inquiries;
        this.roles = roles;
        this.chatMessages = chatMessages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Inquiry> getInquiries() {
        return inquiries;
    }

    public void setInquiries(List<Inquiry> inquiries) {
        this.inquiries = inquiries;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
   public void setRoles(Set<String> roles) {
        this.roles = roles;
   }
   public Set<String> getRoles() {
        return roles;
   }
    public List<ChatMessageDTO> getChatMessages() { return chatMessages; }
    public void setChatMessages(List<ChatMessageDTO> chatMessages) { this.chatMessages = chatMessages; }
}

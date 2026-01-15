package com.mohamedbob.estate.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InquiryDTO {
    private Long id;
    private Long userId;
    private String userName;
    private Long propertyId;
    private String propertyName;
    private String phoneNumber;
    private String message;
    private String response;
    private boolean answered;
    private LocalDateTime created;

    public InquiryDTO() {}

    public InquiryDTO(Long id, Long userId, String userName, String phoneNumber,
                      Long propertyId, String propertyName,
                      String message, String response,
                      boolean answered, LocalDateTime created) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.message = message;
        this.response = response;
        this.answered = answered;
        this.created = created;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Long getPropertyId() {
        return propertyId;
    }
    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
    public String getPropertyName() {
        return propertyName;
    }
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
    public boolean isAnswered() {
        return answered;
    }
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
    public LocalDateTime getCreated() {
        return created;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

}

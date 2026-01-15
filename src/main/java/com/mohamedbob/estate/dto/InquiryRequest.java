package com.mohamedbob.estate.dto;

public class InquiryRequest {
    private Long userId;
    private Long propertyId;
    private String message;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getPropertyId() { return propertyId; }
    public void setPropertyId(Long propertyId) { this.propertyId = propertyId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

package com.mohamedbob.estate.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;


public class TestimonialDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String fullName;

    @NotBlank(message = "Message is required")
    private String message;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private int rating;

    private String imageUrl;

    private boolean approved = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    public TestimonialDTO() {

    }
    public TestimonialDTO(String fullName, String message, int rating, String imageUrl, boolean approved) {
        this.fullName = fullName;
        this.message = message;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.approved = approved;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

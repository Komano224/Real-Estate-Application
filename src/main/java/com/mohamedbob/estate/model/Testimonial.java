package com.mohamedbob.estate.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "testimonials")
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String message;
    private int rating;
    private String imageUrl;
    private boolean approved = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Testimonial() {

    }
    public Testimonial(String fullName, String message, int rating, String imageUrl, boolean approved) {
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

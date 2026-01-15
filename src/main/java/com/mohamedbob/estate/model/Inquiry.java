package com.mohamedbob.estate.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "inquiries")
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(length = 2000, nullable = false)
    private String message;

    @Column(length = 2000)
    private String response;

    private boolean status = false;
    private boolean answered = false;

    @CreationTimestamp
    private LocalDateTime created;

    public Inquiry() {}

    public Inquiry(User user, Property property, String message) {
        this.user = user;
        this.property = property;
        this.message = message;
        this.status = false;
        this.answered = false;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Property getProperty() {
        return property;
    }
    public void setProperty(Property property) {
        this.property = property;
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

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
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

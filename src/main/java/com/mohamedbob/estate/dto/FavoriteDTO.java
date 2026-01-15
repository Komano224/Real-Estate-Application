package com.mohamedbob.estate.dto;

import java.time.LocalDateTime;

public class FavoriteDTO {

    private Long id;
    private Long userId;
    private Long propertyId;
    private PropertyDTO property;
    private LocalDateTime created;

    public FavoriteDTO() {
    }

    public FavoriteDTO(Long id, Long userId, Long propertyId, PropertyDTO property , LocalDateTime created) {
        this.id = id;
        this.userId = userId;
        this.propertyId = propertyId;
        this.property = property;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public PropertyDTO getProperty() {
        return property;
    }
    public void setProperty(PropertyDTO property) {
        this.property = property;
    }
}

package com.mohamedbob.estate.dto;

import java.time.LocalDateTime;

public class FavoriteWithPropertyDTO {
    private Long id;
    private LocalDateTime created;
    private PropertyDTO property;

    public FavoriteWithPropertyDTO() {}

    public FavoriteWithPropertyDTO(Long id, LocalDateTime created, PropertyDTO property) {
        this.id = id;
        this.created = created;
        this.property = property;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }

    public PropertyDTO getProperty() { return property; }
    public void setProperty(PropertyDTO property) { this.property = property; }
}

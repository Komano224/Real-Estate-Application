package com.mohamedbob.estate.dto;

public class PropertyImageDTO {
    private Long id;
    private String image;
    private boolean isMain;
    private Long propertyId;

    public PropertyImageDTO() {
    }

    public PropertyImageDTO(Long id, String image, boolean isMain, Long propertyId) {
        this.id = id;
        this.image = image;
        this.isMain = isMain;
        this.propertyId = propertyId;
    }

    public PropertyImageDTO(Long id, String image, boolean isMain) {
        this.id = id;
        this.image = image;
        this.isMain = isMain;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public boolean isMain() {
        return isMain;
    }
    public void setMain(boolean main) {
        isMain = main;
    }

    public Long getPropertyId() {
        return propertyId;
    }
    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}

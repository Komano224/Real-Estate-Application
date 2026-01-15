package com.mohamedbob.estate.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PropertyDTO {
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private String type;
    private String bedrooms;
    private String bathrooms;
    private String area;
    private String address;
    private String city;
    private String latitude;
    private String longitude;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long userId;
    private UserDTO user;

    private List<PropertyImageDTO> images;

    private List<FavoriteDTO> favorites;

    private List<InquiryDTO> inquiries;

    public PropertyDTO() {
    }


    public PropertyDTO (Long id, String title, String description, Integer price, String type,
                       String bedrooms, String bathrooms, String area, String address, String city,
                       String latitude, String longitude, String status, LocalDateTime createdAt,
                       LocalDateTime updatedAt, Long userId, UserDTO user, List<PropertyImageDTO> images,
                       List<FavoriteDTO> favorites, List<InquiryDTO> inquiries) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.type = type;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.area = area;
        this.address = address;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.user = user;
        this.images = images;
        this.favorites = favorites;
        this.inquiries = inquiries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<PropertyImageDTO> getImages() {
        return images;
    }

    public void setImages(List<PropertyImageDTO> images) {
        this.images = images;
    }

    public List<FavoriteDTO> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<FavoriteDTO> favorites) {
        this.favorites = favorites;
    }

    public List<InquiryDTO> getInquiries() {
        return inquiries;
    }

    public void setInquiries(List<InquiryDTO> inquiries) {
        this.inquiries = inquiries;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}

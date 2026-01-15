package com.mohamedbob.estate.mapper;

import com.mohamedbob.estate.dto.*;
import com.mohamedbob.estate.model.Property;

import java.util.Collections;
import java.util.stream.Collectors;

public class PropertyMapper {

    public static PropertyDTO toDTO(Property property) {
        if (property == null) return null;

        PropertyDTO dto = new PropertyDTO();
        dto.setId(property.getId());
        dto.setTitle(property.getTitle());
        dto.setDescription(property.getDescription());
        dto.setPrice(property.getPrice());
        dto.setType(property.getType());
        dto.setBathrooms(property.getBathrooms());
        dto.setBedrooms(property.getBedrooms());
        dto.setArea(property.getArea());
        dto.setAddress(property.getAddress());
        dto.setCity(property.getCity());
        dto.setLatitude(property.getLatitude());
        dto.setLongitude(property.getLongitude());
        dto.setStatus(property.getStatus());
        dto.setUserId(property.getUser() != null ? property.getUser().getId() : null);
         if(property.getUser() != null) {
             UserDTO userDTO = new UserDTO();
             userDTO.setId(property.getUser().getId());
             userDTO.setName(property.getUser().getName());
             userDTO.setEmail(property.getUser().getEmail());
             userDTO.setPhoneNumber(property.getUser().getPhoneNumber());
             dto.setUser(userDTO);
         }
        // Images
        dto.setImages(property.getPropertyImages() != null
                ? property.getPropertyImages().stream()
                .map(im -> new PropertyImageDTO(im.getId(), im.getImage(), im.isMain(), im.getId()))
                .collect(Collectors.toList())
                : Collections.emptyList());

        // Favorites
        dto.setFavorites(property.getFavorites() != null
                ? property.getFavorites().stream()
                .map(f -> {
                    FavoriteDTO fDto = new FavoriteDTO();
                    fDto.setId(f.getId());
                    fDto.setUserId(f.getUser() != null ? f.getUser().getId() : null);
                    fDto.setPropertyId(f.getProperty() != null ? f.getProperty().getId() : null);
                    fDto.setCreated(f.getCreated());
                    return fDto;
                }).collect(Collectors.toList())
                : Collections.emptyList());

        // Inquiries
        dto.setInquiries(property.getInquiries() != null
                ? property.getInquiries().stream()
                .map(inq -> {
                    InquiryDTO inqDto = new InquiryDTO();
                    inqDto.setId(inq.getId());
                    inqDto.setUserId(inq.getUser() != null ? inq.getUser().getId() : null);
                    inqDto.setUserName(inq.getUser() != null ? inq.getUser().getName() : "");
                    inqDto.setPropertyId(property.getId());
                    inqDto.setPropertyName(property.getTitle() != null ? property.getTitle() : "");
                    inqDto.setMessage(inq.getMessage() != null ? inq.getMessage() : "");
                    inqDto.setResponse(inq.getResponse() != null ? inq.getResponse() : "");
                    inqDto.setAnswered(inq.isAnswered());
                    inqDto.setCreated(inq.getCreated());
                    return inqDto;
                }).collect(Collectors.toList())
                : Collections.emptyList());
        return dto;
    }

    public static Property toEntity(PropertyDTO dto) {
        if (dto == null) return null;

        Property property = new Property();
        property.setTitle(dto.getTitle());
        property.setDescription(dto.getDescription());
        property.setPrice(dto.getPrice());
        property.setType(dto.getType());
        property.setBathrooms(dto.getBathrooms());
        property.setBedrooms(dto.getBedrooms());
        property.setArea(dto.getArea());
        property.setAddress(dto.getAddress());
        property.setCity(dto.getCity());
        property.setLatitude(dto.getLatitude());
        property.setLongitude(dto.getLongitude());
        property.setStatus(dto.getStatus());
        property.setCreatedAt(dto.getCreatedAt());
        property.setUpdatedAt(dto.getUpdatedAt());

        return property;
    }
}

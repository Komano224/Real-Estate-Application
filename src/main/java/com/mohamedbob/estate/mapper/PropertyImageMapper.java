package com.mohamedbob.estate.mapper;

import com.mohamedbob.estate.dto.PropertyImageDTO;
import com.mohamedbob.estate.model.PropertyImage;
import com.mohamedbob.estate.repository.PropertyRepository;

public class PropertyImageMapper {

    private final PropertyRepository propertyRepository;

    public PropertyImageMapper(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    // Mapper PropertyImage -> PropertyImageDTO
    public static PropertyImageDTO toDTO(PropertyImage image) {
        if (image == null) return null;

        return new PropertyImageDTO(
                image.getId(),
                image.getImage(),
                image.isMain(),
                image.getProperty() != null ? image.getProperty().getId() : null
        );
    }

    // Mapper PropertyImageDTO -> PropertyImage
    public static PropertyImage toEntity(PropertyImageDTO dto, PropertyRepository propertyRepository) {
        if (dto == null) return null;

        PropertyImage image = new PropertyImage();
        image.setId(dto.getId());
        image.setImage(dto.getImage());
        image.setMain(dto.isMain());

        if (dto.getPropertyId() != null) {
            propertyRepository.findById(dto.getPropertyId())
                    .ifPresent(image::setProperty);
        }

        return image;
    }
}

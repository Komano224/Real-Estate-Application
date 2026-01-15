package com.mohamedbob.estate.mapper;

import com.mohamedbob.estate.dto.FavoriteDTO;
import com.mohamedbob.estate.dto.PropertyDTO;
import com.mohamedbob.estate.dto.PropertyImageDTO;
import com.mohamedbob.estate.model.Favorite;
import com.mohamedbob.estate.repository.PropertyRepository;
import com.mohamedbob.estate.repository.UserRepository;

public class FavoriteMapper {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    public FavoriteMapper(UserRepository userRepository, PropertyRepository propertyRepository) {
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
    }

    // To FavoriteDTO
    public FavoriteDTO toDTO(Favorite favorite) {
        if (favorite == null) {
            return null;
        }
        PropertyDTO propertyDTO = null;
        if (favorite.getProperty() != null) {
            propertyDTO = new PropertyDTO();
            propertyDTO.setId(favorite.getProperty().getId());
            propertyDTO.setPrice(favorite.getProperty().getPrice());
            propertyDTO.setTitle(favorite.getProperty().getTitle());
            propertyDTO.setCity(favorite.getProperty().getCity());
            propertyDTO.setLatitude(favorite.getProperty().getLatitude());
            propertyDTO.setLongitude(favorite.getProperty().getLongitude());
            propertyDTO.setDescription(favorite.getProperty().getDescription());

            if (favorite.getProperty().getPropertyImages() != null){
                propertyDTO.setImages(
                        favorite.getProperty().getPropertyImages()
                                .stream().map(
                                        img -> new PropertyImageDTO(img.getId(), img.getImage(), img.isMain())
                                ).toList()
                );
            }
        }

        return new FavoriteDTO(
                favorite.getId(),
                favorite.getUser() != null ? favorite.getUser().getId() : null,
                favorite.getProperty() != null ? favorite.getProperty().getId() : null,
                propertyDTO,
                favorite.getCreated()
        );
    }

    // To Entity
    public Favorite toEntity(FavoriteDTO dto) {
        if (dto == null) return null;

        Favorite favorite = new Favorite();
        favorite.setId(dto.getId());

        if (dto.getUserId() != null) {
            userRepository.findById(dto.getUserId()).ifPresent(favorite::setUser);
        }

        if (dto.getPropertyId() != null) {
            propertyRepository.findById(dto.getPropertyId()).ifPresent(favorite::setProperty);
        }

        return favorite;
    }
}

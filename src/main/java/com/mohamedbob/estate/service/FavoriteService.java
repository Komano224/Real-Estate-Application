package com.mohamedbob.estate.service;

import com.mohamedbob.estate.dto.FavoriteDTO;
import com.mohamedbob.estate.mapper.FavoriteMapper;
import com.mohamedbob.estate.model.Favorite;
import com.mohamedbob.estate.model.Property;
import com.mohamedbob.estate.model.User;
import com.mohamedbob.estate.repository.FavoriteRepository;
import com.mohamedbob.estate.repository.PropertyRepository;
import com.mohamedbob.estate.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final FavoriteMapper favoriteMapper;

    public FavoriteService(FavoriteRepository favoriteRepository,
                           UserRepository userRepository,
                           PropertyRepository propertyRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.favoriteMapper = new FavoriteMapper(userRepository, propertyRepository);
    }

    public FavoriteDTO addFavorite(Long userId, Long propertyId) {
        if (favoriteRepository.existsByUserIdAndPropertyId(userId, propertyId)) {
            throw new IllegalArgumentException("Favorite already exists");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + propertyId));

        Favorite favorite = new Favorite(user, property);
        Favorite saved = favoriteRepository.save(favorite);
        return favoriteMapper.toDTO(saved);
    }

    public void removeFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<FavoriteDTO> getFavoritesByUser(Long userId) {
        return favoriteRepository.findByUserId(userId)
                .stream()
                .map(favoriteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FavoriteDTO> getFavoritesByProperty(Long propertyId) {
        return favoriteRepository.findByPropertyId(propertyId)
                .stream()
                .map(favoriteMapper::toDTO) // <-- et ici
                .collect(Collectors.toList());
    }
    // Get all favorites
    @Transactional(readOnly = true)
    public List<FavoriteDTO> getAllFavorites() {
        return  favoriteRepository.findAll().stream()
                .map(favoriteMapper::toDTO)
                .collect(Collectors.toList());
    }
}

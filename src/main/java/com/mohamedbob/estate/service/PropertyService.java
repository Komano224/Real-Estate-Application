package com.mohamedbob.estate.service;

import com.mohamedbob.estate.dto.PropertyDTO;
import com.mohamedbob.estate.mapper.PropertyMapper;
import com.mohamedbob.estate.model.Property;
import com.mohamedbob.estate.model.User;
import com.mohamedbob.estate.repository.PropertyRepository;
import com.mohamedbob.estate.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public PropertyService(PropertyRepository propertyRepository, UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    // Create
    public PropertyDTO createProperty(PropertyDTO propertyDTO) {
        User user = userRepository.findById(propertyDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + propertyDTO.getUserId()));

        Property property = PropertyMapper.toEntity(propertyDTO);
        property.setUser(user);
        property.setStatus("AVAILABLE");

        Property saved = propertyRepository.save(property);
        return PropertyMapper.toDTO(saved);
    }

    // Get all
    @Transactional(readOnly = true)
    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(PropertyMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find by id
    @Transactional(readOnly = true)
    public PropertyDTO getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));
        return PropertyMapper.toDTO(property);
    }

    // Update
    public PropertyDTO updateProperty(Long id, PropertyDTO updatedPropertyDTO) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));

        existing.setTitle(updatedPropertyDTO.getTitle());
        existing.setDescription(updatedPropertyDTO.getDescription());
        existing.setPrice(updatedPropertyDTO.getPrice());
        existing.setType(updatedPropertyDTO.getType());
        existing.setBedrooms(updatedPropertyDTO.getBedrooms());
        existing.setBathrooms(updatedPropertyDTO.getBathrooms());
        existing.setArea(updatedPropertyDTO.getArea());
        existing.setAddress(updatedPropertyDTO.getAddress());
        existing.setCity(updatedPropertyDTO.getCity());
        existing.setLatitude(updatedPropertyDTO.getLatitude());
        existing.setLongitude(updatedPropertyDTO.getLongitude());
        existing.setStatus(updatedPropertyDTO.getStatus());

        Property updated = propertyRepository.save(existing);
        return PropertyMapper.toDTO(updated);
    }

    // Delete the property
    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new EntityNotFoundException("Property not found with id: " + id);
        }
        propertyRepository.deleteById(id);
    }

    // Find by town
    @Transactional(readOnly = true)
    public List<PropertyDTO> findByCity(String city) {
        return propertyRepository.findByCityIgnoreCase(city)
                .stream()
                .map(PropertyMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find by type
    @Transactional(readOnly = true)
    public List<PropertyDTO> findByType(String type) {
        return propertyRepository.findByTypeIgnoreCase(type)
                .stream()
                .map(PropertyMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find by status
    @Transactional(readOnly = true)
    public List<PropertyDTO> findByStatus(String status) {
        return propertyRepository.findByStatusIgnoreCase(status)
                .stream()
                .map(PropertyMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Find by user
    @Transactional(readOnly = true)
    public List<PropertyDTO> findByUserId(Long userId) {
        return propertyRepository.findByUserId(userId)
                .stream()
                .map(PropertyMapper::toDTO)
                .collect(Collectors.toList());
    }
}

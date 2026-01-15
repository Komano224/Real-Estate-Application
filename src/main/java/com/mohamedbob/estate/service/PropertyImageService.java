package com.mohamedbob.estate.service;

import com.mohamedbob.estate.dto.PropertyImageDTO;
import com.mohamedbob.estate.mapper.PropertyImageMapper;
import com.mohamedbob.estate.model.Property;
import com.mohamedbob.estate.model.PropertyImage;
import com.mohamedbob.estate.repository.PropertyImageRepository;
import com.mohamedbob.estate.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PropertyImageService {
    private final PropertyImageRepository propertyImageRepository;
    private final Path uploadDir = Paths.get("uploads");
    private final PropertyRepository propertyRepository;

    public PropertyImageService(PropertyImageRepository propertyImageRepository, PropertyRepository propertyRepository) throws  IOException {
        this.propertyImageRepository = propertyImageRepository;
        if(!Files.exists(uploadDir)) Files.createDirectories(uploadDir);
        this.propertyRepository = propertyRepository;
    }

    // Get All properties
    @Transactional(readOnly = true)
    public List<PropertyImageDTO> getAllPropertyImages(){
        List<PropertyImage> propertyImages = propertyImageRepository.findAll();

        return propertyImages.stream().map(PropertyImageMapper::toDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    // Get by id
    @Transactional(readOnly = true)
    public PropertyImageDTO getImageById(Long id) {
        PropertyImage image = propertyImageRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No image found with id: " + id)
        );

        return PropertyImageMapper.toDTO(image);
    }

    // Create the propertyImage
    public PropertyImageDTO createPropertyImage(Long propertyId, MultipartFile file, boolean isMain) throws IOException {
        Property property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new RuntimeException("Property not found with id: " + propertyId)
        );

        // Upload the file
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), uploadDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

        // Reset old main image if needed
        if(isMain){
         propertyImageRepository.findByPropertyIdAndIsMainTrue(propertyId)
                 .forEach(img -> {
                     img.setMain(false);
                     propertyImageRepository.save(img);
                 });
        }

        PropertyImage image = new PropertyImage();
        image.setImage(fileName);
        image.setMain(isMain);
        image.setProperty(property);

        return PropertyImageMapper.toDTO(propertyImageRepository.save(image));
    }

    // Update image
    public PropertyImageDTO updatePropertyImage(Long id, MultipartFile file, boolean isMain) throws IOException {
        PropertyImage image = propertyImageRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No image found with id: " + id)
        );
        if(file !=null && !file.isEmpty()){
            if(image.getImage() !=null){
                Files.deleteIfExists(uploadDir.resolve(image.getImage()));
            }
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), uploadDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            image.setImage(fileName);
        }
        if(isMain && !image.isMain()){
            propertyImageRepository.findByPropertyIdAndIsMainTrue(image.getId())
                    .forEach(img -> {
                        img.setMain(false);
                        propertyImageRepository.save(img);
                    });
            image.setMain(true);
        }else {
            image.setMain(isMain);
        }

        return PropertyImageMapper.toDTO(propertyImageRepository.save(image));
    }

    // Delete image
    public void deletePropertyImage(Long id) throws IOException {
        PropertyImage image = propertyImageRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No image found with id: " + id)
        );
        if(image.getImage() !=null){
            Files.deleteIfExists(uploadDir.resolve(image.getImage()));
        }
        propertyImageRepository.delete(image);
    }

    // Get All images for a property
    @Transactional(readOnly = true)
    public List<PropertyImageDTO> getImagesByProperty(Long propertyId){

        return propertyImageRepository.findByPropertyId(propertyId).stream()
                .map(PropertyImageMapper::toDTO)
                .collect(Collectors.toList());
    }
}

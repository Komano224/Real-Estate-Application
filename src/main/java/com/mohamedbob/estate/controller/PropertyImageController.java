package com.mohamedbob.estate.controller;

import com.mohamedbob.estate.dto.PropertyImageDTO;
import com.mohamedbob.estate.service.PropertyImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/property-images")
public class PropertyImageController {
    private final PropertyImageService propertyImageService;

    public PropertyImageController(PropertyImageService propertyImageService) {
        this.propertyImageService = propertyImageService;
    }

    @GetMapping
    public ResponseEntity<List<PropertyImageDTO>> getAllImages() {
        return  ResponseEntity.ok(propertyImageService.getAllPropertyImages());
    }

    // Get image by id
    @GetMapping("/{id}")
    public ResponseEntity<PropertyImageDTO> getImageById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyImageService.getImageById(id));
    }
    // Get all images for a property
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<PropertyImageDTO>> getImageByProperty(@PathVariable Long propertyId) {

        return ResponseEntity.ok(propertyImageService.getImagesByProperty(propertyId));
    }

    // Create new image
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PropertyImageDTO> uploadImage(
            @RequestParam Long propertyId,
            @RequestParam("image") MultipartFile file,
            @RequestParam(defaultValue = "false") boolean isMain
    ) throws IOException {
        PropertyImageDTO dto = propertyImageService.createPropertyImage(propertyId, file, isMain);
        return ResponseEntity.ok(dto);
    }

    // Update image
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PropertyImageDTO> updateImage(
            @PathVariable Long id,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam(defaultValue = "false") boolean isMain
    ) throws IOException
    {
        PropertyImageDTO dto = propertyImageService.updatePropertyImage(id,file,isMain);
        return ResponseEntity.ok(dto);
    }

    // Delete image
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) throws IOException {
        propertyImageService.deletePropertyImage(id);
        return ResponseEntity.ok("Image deleted successfully");
    }
}

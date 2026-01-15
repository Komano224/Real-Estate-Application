package com.mohamedbob.estate.controller;

import com.mohamedbob.estate.dto.PropertyDTO;
import com.mohamedbob.estate.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody @Valid PropertyDTO propertyDTO) {
        PropertyDTO created = propertyService.createProperty(propertyDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(
            @PathVariable Long id,
           @Valid @RequestBody PropertyDTO propertyDTO
    ) {
        PropertyDTO updated = propertyService.updateProperty(id, propertyDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<PropertyDTO>> findByCity(@PathVariable String city) {
        return ResponseEntity.ok(propertyService.findByCity(city));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<PropertyDTO>> findByType(@PathVariable String type) {
        return ResponseEntity.ok(propertyService.findByType(type));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PropertyDTO>> findByStatus(@PathVariable String status) {
        return ResponseEntity.ok(propertyService.findByStatus(status));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PropertyDTO>> findByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(propertyService.findByUserId(userId));
    }
}

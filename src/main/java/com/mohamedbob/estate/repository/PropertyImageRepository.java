package com.mohamedbob.estate.repository;

import com.mohamedbob.estate.model.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {
    List<PropertyImage> findByPropertyId(Long propertyId);


    List<PropertyImage> findByPropertyIdAndIsMainTrue(Long propertyId);
}

package com.mohamedbob.estate.repository;

import com.mohamedbob.estate.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByCityIgnoreCase(String city);

    List<Property> findByTypeIgnoreCase(String type);

    List<Property> findByStatusIgnoreCase(String status);

    List<Property> findByUserId(Long userId);
}

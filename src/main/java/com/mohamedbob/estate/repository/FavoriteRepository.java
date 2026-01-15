package com.mohamedbob.estate.repository;

import com.mohamedbob.estate.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    boolean existsByUserIdAndPropertyId(Long userId, Long propertyId);

    List<Favorite> findByUserId(Long userId);

    List<Favorite> findByPropertyId(Long propertyId);
}

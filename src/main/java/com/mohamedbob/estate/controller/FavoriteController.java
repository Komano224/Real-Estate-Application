package com.mohamedbob.estate.controller;

import com.mohamedbob.estate.dto.FavoriteDTO;
import com.mohamedbob.estate.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ResponseEntity<FavoriteDTO> addFavorite(@RequestParam Long userId, @RequestParam Long propertyId) {
        FavoriteDTO dto = favoriteService.addFavorite(userId, propertyId);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long id) {
        favoriteService.removeFavorite(id);
        return ResponseEntity.ok("Favorite removed successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteService.getFavoritesByUser(userId));
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesByProperty(@PathVariable Long propertyId) {
        return ResponseEntity.ok(favoriteService.getFavoritesByProperty(propertyId));
    }
    @GetMapping()
    public ResponseEntity<List<FavoriteDTO>> getFavorites() {
        return  ResponseEntity.ok(favoriteService.getAllFavorites());
    }
}

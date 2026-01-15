package com.mohamedbob.estate.controller;

import com.mohamedbob.estate.dto.SettingDTO;
import com.mohamedbob.estate.service.SettingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settings")
public class SettingController {

    private final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    // Get all settings
    @GetMapping
    public ResponseEntity<List<SettingDTO>> getAllSettings() {
        List<SettingDTO> settings = settingService.getAllSetting();
        return ResponseEntity.ok(settings);
    }

    // Get setting by ID
    @GetMapping("/{id}")
    public ResponseEntity<SettingDTO> getSettingById(@PathVariable Long id) {
        SettingDTO setting = settingService.getSettingById(id);
        return ResponseEntity.ok(setting);
    }

    // Create a new setting
    @PostMapping
    public ResponseEntity<SettingDTO> createSetting(@RequestBody SettingDTO settingDTO) {
        SettingDTO savedSetting = settingService.saveSetting(settingDTO);
        return ResponseEntity.ok(savedSetting);
    }

    // Update a setting
    @PutMapping("/{id}")
    public ResponseEntity<SettingDTO> updateSetting(
            @PathVariable Long id,
            @RequestBody SettingDTO settingDTO) {
        // Assure que le DTO a l'ID correct
        settingDTO.setId(id);
        SettingDTO updatedSetting = settingService.updateSetting(settingDTO);
        return ResponseEntity.ok(updatedSetting);
    }

    // Delete a setting
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetting(@PathVariable Long id) {
        settingService.deleteSetting(id);
        return ResponseEntity.noContent().build();
    }
}

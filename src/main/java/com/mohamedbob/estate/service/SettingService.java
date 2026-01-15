package com.mohamedbob.estate.service;

import com.mohamedbob.estate.dto.SettingDTO;
import com.mohamedbob.estate.mapper.SettingMapper;
import com.mohamedbob.estate.model.Setting;
import com.mohamedbob.estate.repository.SettingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettingService {
    private final SettingRepository settingRepository;

    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Transactional(readOnly = true)
    public List<SettingDTO> getAllSetting(){
        List<Setting> settings = settingRepository.findAll();
        return settings.stream().map(SettingMapper::toDTO).collect(Collectors.toList());
    }

    // Get setting by ID
    @Transactional(readOnly = true)
    public SettingDTO getSettingById(Long id){
        Setting setting = settingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setting not found with id: " + id));
        return SettingMapper.toDTO(setting);
    }


    // Create a setting
    public SettingDTO saveSetting(SettingDTO settingDTO){
        Setting setting = SettingMapper.toEntity(settingDTO);
        settingRepository.save(setting);
        return SettingMapper.toDTO(setting);
    }

    // Update the setting
    @Transactional
    public SettingDTO updateSetting(SettingDTO settingDTO){
        Setting existingSetting = settingRepository.findById(settingDTO.getId())
                .orElseThrow(() -> new RuntimeException("Setting not found with id: " + settingDTO.getId()));

        existingSetting.setKey(settingDTO.getKey());
        existingSetting.setValue(settingDTO.getValue());

        settingRepository.save(existingSetting);
        return SettingMapper.toDTO(existingSetting);
    }


    // Delete the setting
    public void deleteSetting(Long id) {
        settingRepository.deleteById(id);
    }
}

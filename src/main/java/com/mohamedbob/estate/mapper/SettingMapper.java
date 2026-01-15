package com.mohamedbob.estate.mapper;

import com.mohamedbob.estate.dto.SettingDTO;
import com.mohamedbob.estate.model.Setting;

public class SettingMapper {

    public static SettingDTO toDTO(Setting setting) {
        if (setting == null) return null;

        SettingDTO dto = new SettingDTO();
        dto.setId(setting.getId());
        dto.setKey(setting.getKey());
        dto.setValue(setting.getValue());
        return dto;
    }

    public static Setting toEntity(SettingDTO dto) {
        if (dto == null) return null;

        Setting setting = new Setting();
        setting.setId(dto.getId());
        setting.setKey(dto.getKey());
        setting.setValue(dto.getValue());
        return setting;
    }
}

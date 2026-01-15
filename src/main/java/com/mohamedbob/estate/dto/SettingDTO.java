package com.mohamedbob.estate.dto;

import jakarta.persistence.Column;

public class SettingDTO {

    private Long id;

    @Column(unique = true, nullable = false)
    private String key;

    @Column(nullable = false)
    private String value;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}

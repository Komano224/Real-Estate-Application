package com.mohamedbob.estate.repository;

import com.mohamedbob.estate.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Long> {
}

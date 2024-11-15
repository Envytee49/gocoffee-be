package com.example.gocoffee.repository;

import com.example.gocoffee.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, String> {
}

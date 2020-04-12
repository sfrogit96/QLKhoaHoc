package com.th.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.th.entity.ChucVu;
import com.th.entity.User;

public interface ChucVuRepository extends JpaRepository<ChucVu, Integer>{

	ChucVu findByTenchucvu(String tenchucvu);
}

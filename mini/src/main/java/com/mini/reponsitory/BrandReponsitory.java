package com.mini.reponsitory;

import com.mini.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandReponsitory extends JpaRepository<Brand, Long> {
}

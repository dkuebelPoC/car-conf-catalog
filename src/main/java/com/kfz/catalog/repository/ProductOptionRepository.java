package com.kfz.catalog.repository;

import com.kfz.catalog.model.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductOptionRepository extends JpaRepository<ProductOption, UUID> {
}

package com.backend.modelsis.repository;

import com.backend.modelsis.entites.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
}

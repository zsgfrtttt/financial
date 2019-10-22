package com.csz.manager.repositories;

import com.csz.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product,String>, JpaSpecificationExecutor<Product> {
}

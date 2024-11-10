package org.platform.repository;

import org.platform.entity.AccountEntity;
import org.platform.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    ProductEntity getByProductNameOrProductCode(String productName, String productCode);
}

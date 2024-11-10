package org.platform.service;

import org.platform.model.Product;

import java.util.UUID;

public interface ProductService {
    Product saveProduct(Product product, UUID product_id);
}

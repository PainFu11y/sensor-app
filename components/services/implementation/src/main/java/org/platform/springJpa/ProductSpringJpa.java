package org.platform.springJpa;

import org.platform.entity.ProductEntity;
import org.platform.model.Product;
import org.platform.repository.ProductRepository;
import org.platform.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductSpringJpa implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product, UUID productId) {
        try {
            ProductEntity productEntity = (productId != null) ? updateProduct(product, productId) : createProduct(product);
            return productRepository.save(productEntity).toProduct();
        } catch (Exception e) {
            throw new RuntimeException("Problem during save product", e);
        }
    }

    private ProductEntity updateProduct(Product product, UUID productId) {
        ProductEntity existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Wrong product_id"));

        checkProductExists(product, productId);

        existingProduct.setProductName(product.getProductName());
        existingProduct.setDescription(product.getProductDescription());
        existingProduct.setProductCode(product.getProductCode());

        return existingProduct;
    }

    private ProductEntity createProduct(Product product) {
        checkProductExists(product, null);
        return new ProductEntity(product);
    }

    private void checkProductExists(Product product, UUID productId) {
        ProductEntity existingProduct = productRepository.getByProductNameOrProductCode(product.getProductName(), product.getProductCode());
        if (existingProduct != null && (productId == null || !existingProduct.getProductId().equals(productId))) {
            throw new RuntimeException("Product already exists");
        }
    }
}

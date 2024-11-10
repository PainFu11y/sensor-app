package org.platform.controller;

import org.platform.constants.RoutConstants;
import org.platform.model.Product;
import org.platform.repository.ProductRepository;
import org.platform.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(RoutConstants.BASE_URL + RoutConstants.VERSION + RoutConstants.PRODUCTS)
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public @ResponseBody Product addProduct(@RequestBody Product product,@RequestBody UUID productId) {

        return productService.saveProduct(product,productId) ;

    }
}

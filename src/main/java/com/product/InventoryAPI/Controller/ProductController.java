package com.product.InventoryAPI.Controller;


import com.product.InventoryAPI.Entity.Product;
import com.product.InventoryAPI.Services.ProductService;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Create Product
    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {

        Product newProduct = productService.createProduct(product);
        log.info("Product created with ID: {} and SKU: {}", newProduct.getId(), newProduct.getSku());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newProduct);
    }


    //Get All Products
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> newProduct = productService.getAllProducts();
        log.info("Product List Size is : {}", newProduct.size());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(newProduct);
    }

    //Get Product By ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        Product newProduct = productService.getProductById(id);
        log.info("Product with ID: {} and SKU: {}", id, newProduct.getSku());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(newProduct);
    }

    //Update Product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                 @Valid @RequestBody Product product) {
        Product newProduct = productService.updateProduct(id, product);
        log.info("Product Updated with ID: {} and SKU: {}", id, newProduct.getSku());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(newProduct);
    }

    //Delete Product
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String newProduct = productService.deleteProduct(id);
        log.info("Product Deleted with ID: {} ", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(newProduct);
    }
}
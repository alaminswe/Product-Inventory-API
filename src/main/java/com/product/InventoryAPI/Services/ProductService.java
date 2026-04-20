package com.product.InventoryAPI.Services;
import com.product.InventoryAPI.Entity.Product;
import com.product.InventoryAPI.Exceptions.InvalidSkuFormatException;
import com.product.InventoryAPI.Exceptions.ProductNotFoundException;
import com.product.InventoryAPI.Exceptions.SkuAlreadyExistsException;
import com.product.InventoryAPI.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private static final String SKU_PATTERN = "^SKU-[A-Za-z0-9]{8}$";

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create Product
    public Product createProduct(Product product) {
        validateSku(product.getSku());
        if (productRepository.existsBySku(product.getSku())) {
            throw new SkuAlreadyExistsException ("sku");
        }
        return productRepository.save(product);
    }

    // Get All Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get Product By ID
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    // Update Product
    public Product updateProduct(Long id, Product updatedProduct) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        // SKU change allowed
        if (!existingProduct.getSku().equals(updatedProduct.getSku())) {
            throw new InvalidSkuFormatException("SKU cannot be changed");
        }

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setStatus(updatedProduct.getStatus());

        return productRepository.save(existingProduct);
    }

    // Delete Product
    public String deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        productRepository.delete(product);
        return "Product has been deleted";
    }

    // SKU Validation
    private void validateSku(String sku) {

        if (!Pattern.matches(SKU_PATTERN, sku)) {
            throw new InvalidSkuFormatException(
                    "SKU format invalid. Example: SKU-A1B2C3D4"
            );
        }
    }
}

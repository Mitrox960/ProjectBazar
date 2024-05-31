package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    public Product createProduct(Product product) {
        return productService.createProduct(product);
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public ResponseEntity<Product> getProductById(Long id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Product> updateProduct(Long id, Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    public ResponseEntity<List<Product>> getUnsoldProducts(Double maxPrice, String sortBy) {
        List<Product> unsoldProducts = productService.getUnsoldProducts(maxPrice, sortBy);
        return ResponseEntity.ok(unsoldProducts);
    }

    public ResponseEntity<Void> deleteProduct(Long id) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

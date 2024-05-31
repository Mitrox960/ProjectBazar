package com.example.demo.services;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    public List<Product> getUnsoldProducts(Double maxPrice, String sortBy) {
        if (maxPrice == null) {
            return productRepository.findBySoldFalse(Sort.by(sortBy));
        } else {
            return productRepository.findBySoldFalseAndPriceLessThan(maxPrice, Sort.by(sortBy));
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public List<Product> getAllProductsForUser(User user) {
        return productRepository.findByUser(user);
    }
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription());
        product.setSold(productDetails.isSold());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

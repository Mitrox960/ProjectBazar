package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public User createUser(User user) {
        return userService.createUser(user);
    }

    public ResponseEntity<List<Product>> getAllProductsForUser(Long userId) {
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Product> products = productService.getAllProductsForUser(user);
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<Void> deleteProductForUser(Long userId, Long productId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Product> productOptional = productService.getProductById(productId);
            if (productOptional.isPresent() && productOptional.get().getUser().getId().equals(userId)) {
                productService.deleteProduct(productId);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Product> updateProductForUser(Long userId, Long productId, Product productDetails) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Product> productOptional = productService.getProductById(productId);
            if (productOptional.isPresent() && productOptional.get().getUser().getId().equals(userId)) {
                Product updatedProduct = productService.updateProduct(productId, productDetails);
                return ResponseEntity.ok(updatedProduct);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public ResponseEntity<Void> sellProduct(Long userId, Long productId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Product> productOptional = productService.getProductById(productId);
            if (productOptional.isPresent() && productOptional.get().getUser().getId().equals(userId)) {
                Product product = productOptional.get();
                product.setSold(true);
                productService.updateProduct(productId, product);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    public ResponseEntity<User> getUserById(Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    public ResponseEntity<User> updateUser(Long id, User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }


    public Product createProductForUser(Long userId, Product product) {
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        product.setUser(user);
        return productService.createProduct(product);
    }
}

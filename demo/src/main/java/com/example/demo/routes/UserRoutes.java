package com.example.demo.routes;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRoutes {

    @Autowired
    private UserController userController;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userController.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userController.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userController.getUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userController.updateUser(id, userDetails);
    }

    @DeleteMapping("/{userId}/products/{productId}")
    public ResponseEntity<Void> deleteProductForUser(@PathVariable Long userId, @PathVariable Long productId) {
        return userController.deleteProductForUser(userId, productId);
    }

    @PutMapping("/{userId}/products/{productId}")
    public ResponseEntity<Product> updateProductForUser(@PathVariable Long userId, @PathVariable Long productId, @RequestBody Product productDetails) {
        return userController.updateProductForUser(userId, productId, productDetails);
    }
    @GetMapping("/{userId}/products")
    public ResponseEntity<List<Product>> getAllProductsForUser(@PathVariable Long userId) {
        return userController.getAllProductsForUser(userId);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userController.deleteUser(id);
    }

    @PatchMapping("/{userId}/products/{productId}/sell")
    public ResponseEntity<Void> sellProduct(@PathVariable Long userId, @PathVariable Long productId) {
        return userController.sellProduct(userId, productId);
    }

    @PostMapping("/{userId}/products")
    public Product createProductForUser(@PathVariable Long userId, @RequestBody Product product) {
        return userController.createProductForUser(userId, product);
    }
}

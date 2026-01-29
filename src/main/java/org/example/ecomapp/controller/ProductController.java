package org.example.ecomapp.controller;

import org.example.ecomapp.model.Product;
import org.example.ecomapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet() {
        return "Hello World";
    }

    @RequestMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @RequestMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        System.out.println("prod: " + id + "data requested");
        return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product prod) {

        try {
            Product product = service.addProduct(prod);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product prod) {
        try {
            Product product = service.updateProduct(id, prod);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> delProduct(@PathVariable int id) {
        try {
            Product product = service.getProductById(id);

            if (product != null) {
                service.delProduct(id);
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product not found", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword) {
        System.out.println("Searching with " + keyword);
        List<Product> searchProducts =  service.searchProduct(keyword);
        return new ResponseEntity<List<Product>>(searchProducts, HttpStatus.OK);

    }

}
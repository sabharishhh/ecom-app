package org.example.ecomapp.service;

import org.example.ecomapp.model.Product;
import org.example.ecomapp.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(new Product());
    }

    public Product addProduct(Product prod) {
        return repo.save(prod);
    }

    public Product updateProduct(int id, Product prod) {
        return repo.save(prod);
    }

    public void delProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProduct(String keyword) {
        return repo.searchProduct(keyword);
    }
}

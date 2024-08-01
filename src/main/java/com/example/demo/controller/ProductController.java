package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity <Product> saveProduct(@RequestBody Product product){
        Product newProduct = productService.saveProduct(product);

        return ResponseEntity.ok(newProduct);

    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }


    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id,  @RequestBody Product product){
        return productService.updateProduct(id, product);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }




}

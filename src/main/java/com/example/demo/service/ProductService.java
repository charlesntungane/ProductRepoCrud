package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private  ProductRepository productRepository;


    public Product saveProduct(Product product){
       return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
       return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct ){

        Optional<Product>  existingProduct = productRepository.findById(id);
        if(existingProduct.isPresent()){
           Product product = existingProduct.get();
           product.setName(updatedProduct.getName());
           product.setAge(updatedProduct.getAge());
           return productRepository.save(product);



        }else throw new RuntimeException("Product not Found!");

    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}

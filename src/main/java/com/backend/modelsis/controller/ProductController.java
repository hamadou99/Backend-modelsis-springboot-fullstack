package com.backend.modelsis.controller;

import com.backend.modelsis.entites.Product;
import com.backend.modelsis.services.ProductServices;
import com.backend.modelsis.entites.ProductWithType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
@CrossOrigin("http://localhost:4200")
@Slf4j
public class ProductController {
    private final ProductServices productServices;

    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductWithType productWithType){
        log.info("resquest to addProduct {}");
        return productServices.addProduct(productWithType);
    }
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody ProductWithType productWithType){
        log.info("resquest to updateProduct {}");
        return productServices.updateProduct(id, productWithType);
    }

    @GetMapping("/products")
    public List<Product> findProducts(){
        log.info("resquest to findProducts {}");
        return productServices.findAllProducts();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> findProductByID(@PathVariable int id){
        return productServices.findProductByID(id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id){
        productServices.deleteProduct(id);
    }
}

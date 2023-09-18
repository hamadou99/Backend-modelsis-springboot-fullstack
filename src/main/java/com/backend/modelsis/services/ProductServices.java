package com.backend.modelsis.services;

import com.backend.modelsis.entites.Product;
import com.backend.modelsis.entites.ProductType;
import com.backend.modelsis.repository.ProductRepository;
import com.backend.modelsis.repository.ProductTypeRepository;
import com.backend.modelsis.entites.ProductWithType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServices {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    public List<Product> findAllProducts(){
        log.info("request to findAllProducts {}");
        return productRepository.findAll();
    }

    public Optional<Product> findProductByID(int id){
        log.info("request to findProductByID {}");
        return productRepository.findById(id);
    }

    public Product addProduct(ProductWithType productWithType){
        log.info("request to addProduct {}",productWithType);
        Product product = new Product();

        Optional<ProductType> productType = productTypeRepository.findById(productWithType.getIdProductType());
        productType.ifPresent(product::setProductType);
        product.setName(productWithType.getProductName());
        product.setCreatedDate(LocalDateTime.now());
        return productRepository.save(product);
    }

    public Product updateProduct(int id, ProductWithType productWithType){
        log.info("request to updateProduct {}",productWithType);
        ProductType productType;
        if (productWithType.getIdProductType() != null) {
            Optional<ProductType> OptionalProductType = productTypeRepository.findById(productWithType.getIdProductType());
            productType = OptionalProductType.get();
        } else {
            productType = null;
        }
        return productRepository.findById(id).
                map(product1 -> {
                    product1.setName(productWithType.getProductName());
                    product1.setCreatedDate(LocalDateTime.now());
                    System.out.println(product1);
                    return productRepository.save(product1);
                }).orElseThrow(RuntimeException::new);
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }
}

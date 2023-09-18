package com.backend.modelsis.services;

import com.backend.modelsis.entites.ProductType;
import com.backend.modelsis.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTypeServices {
    private final ProductTypeRepository productTypeRepository;

    public List<ProductType> findAllProductTypes(){
        return productTypeRepository.findAll();
    }

    public Optional<ProductType> findProductTypeByID(int id){
        return productTypeRepository.findById(id);
    }

    public ProductType addProductType(ProductType productType){
        return productTypeRepository.save(productType);
    }

    public ProductType updateProductType(int id, ProductType productType){
        return productTypeRepository.findById(id).
                map(productType1 -> {
                    productType.setName(productType.getName());
                    return productTypeRepository.save(productType1);
                }).orElseThrow(RuntimeException::new);
    }

    public void deleteProductType(int id){
        productTypeRepository.deleteById(id);
    }
}

package com.backend.modelsis.controller;

import com.backend.modelsis.entites.ProductType;
import com.backend.modelsis.services.ProductTypeServices;
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
public class ProductTypeController {
    private final ProductTypeServices productTypeServices;

    @PostMapping("/productType")
    public ProductType addProductType(@RequestBody ProductType productType){
        log.info("resquest to addProductType {}");
        return productTypeServices.addProductType(productType);
    }
    @PutMapping("/productType/{id}")
    public ProductType updateProductType(@PathVariable int id, @RequestBody ProductType productType){
        log.info("resquest to updateProductType {}");
        return productTypeServices.updateProductType(id, productType);
    }

    @GetMapping("/productType")
    public List<ProductType> findProductTypes(){
        log.info("resquest to findProductType {}");
        return productTypeServices.findAllProductTypes();
    }

    @GetMapping("/productType/{id}")
    public Optional<ProductType> findProductTypeByID(@PathVariable int id){
        return productTypeServices.findProductTypeByID(id);
    }

    @DeleteMapping("/productType/{id}")
    public void deleteProductType(@PathVariable int id){
        productTypeServices.deleteProductType(id);
    }
}

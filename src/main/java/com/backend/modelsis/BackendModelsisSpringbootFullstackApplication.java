package com.backend.modelsis;

import com.backend.modelsis.entites.Product;
import com.backend.modelsis.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class BackendModelsisSpringbootFullstackApplication {
    private final ProductRepository productRepository;
    public static void main(String[] args) {
        SpringApplication.run(BackendModelsisSpringbootFullstackApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        productRepository.save(new Product(1,"Laptop"));
//        log.info("insertion reussie");
//
//    }
}

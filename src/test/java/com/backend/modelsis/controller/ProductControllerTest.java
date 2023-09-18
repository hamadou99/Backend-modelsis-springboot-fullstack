package com.backend.modelsis.controller;

import com.backend.modelsis.controller.ProductController;
import com.backend.modelsis.entites.Product;
import com.backend.modelsis.services.ProductServices;
import com.backend.modelsis.entites.ProductWithType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServices productServices;

    @Test
    public void testAddProduct() throws Exception {
        ProductWithType productWithType = new ProductWithType();
        productWithType.setIdProductType(1);
        productWithType.setProductName("TestProduct");

        Product addedProduct = new Product();
        addedProduct.setId(1);
        addedProduct.setName("TestProduct");

        Mockito.when(productServices.addProduct(productWithType)).thenReturn(addedProduct);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idProductType\": 1, \"productName\": \"TestProduct\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TestProduct"));
    }

    @Test
    public void testFindProductByID() throws Exception {
        Product foundProduct = new Product();
        foundProduct.setId(1);
        foundProduct.setName("TestProduct");

        Mockito.when(productServices.findProductByID(1)).thenReturn(Optional.of(foundProduct));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TestProduct"));
    }

    @Test
    public void testFindProducts() throws Exception {
        Product product = new Product();
        product.setId(1);
        product.setName("TestProduct");

        Mockito.when(productServices.findAllProducts()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("TestProduct"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        ProductWithType updatedProduct = new ProductWithType();
        updatedProduct.setIdProductType(1);
        updatedProduct.setProductName("UpdatedTestProduct");

        Product modifiedProduct = new Product();
        modifiedProduct.setId(1);
        modifiedProduct.setName("UpdatedTestProduct");

        Mockito.when(productServices.updateProduct(1, updatedProduct)).thenReturn(modifiedProduct);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idProductType\": 1, \"productName\": \"UpdatedTestProduct\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("UpdatedTestProduct"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/1"))
                .andExpect(status().isOk());
        Mockito.verify(productServices, Mockito.times(1)).deleteProduct(1);
    }
}

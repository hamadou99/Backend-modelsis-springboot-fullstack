package com.backend.modelsis.controller;

import com.backend.modelsis.controller.ProductTypeController;
import com.backend.modelsis.entites.ProductType;
import com.backend.modelsis.services.ProductTypeServices;
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

@WebMvcTest(ProductTypeController.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductTypeServices productTypeServices;

    @Test
    public void testAddProductType() throws Exception {
        ProductType productType = new ProductType();
        productType.setId(1);
        productType.setName("TestProductType");

        Mockito.when(productTypeServices.addProductType(productType)).thenReturn(productType);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/productType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"TestProductType\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TestProductType"));
    }

    @Test
    public void testFindProductTypeByID() throws Exception {
        ProductType foundProductType = new ProductType();
        foundProductType.setId(1);
        foundProductType.setName("TestProductType");

        Mockito.when(productTypeServices.findProductTypeByID(1)).thenReturn(Optional.of(foundProductType));

        mockMvc.perform(MockMvcRequestBuilders.get("/productType/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TestProductType"));
    }

    @Test
    public void testFindProductTypes() throws Exception {
        ProductType productType = new ProductType();
        productType.setId(1);
        productType.setName("TestProductType");

        Mockito.when(productTypeServices.findAllProductTypes()).thenReturn(Collections.singletonList(productType));

        mockMvc.perform(MockMvcRequestBuilders.get("/productType"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("TestProductType"));
    }

    @Test
    public void testUpdateProductType() throws Exception {
        ProductType updatedProductType = new ProductType();
        updatedProductType.setId(1);
        updatedProductType.setName("UpdatedTestProductType");

        Mockito.when(productTypeServices.updateProductType(1, updatedProductType)).thenReturn(updatedProductType);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/productType/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"UpdatedTestProductType\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("UpdatedTestProductType"));
    }

    @Test
    public void testDeleteProductType() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/productType/1"))
                .andExpect(status().isOk());
        Mockito.verify(productTypeServices, Mockito.times(1)).deleteProductType(1);
    }
}
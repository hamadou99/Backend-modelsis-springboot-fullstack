package com.backend.modelsis.services;

import com.backend.modelsis.entites.Product;
import com.backend.modelsis.entites.ProductType;
import com.backend.modelsis.repository.ProductRepository;
import com.backend.modelsis.repository.ProductTypeRepository;
import com.backend.modelsis.entites.ProductWithType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServicesTest {

    @Autowired
    private ProductServices productServices;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductTypeRepository productTypeRepository;

    @BeforeEach
    public void setUp() {
        // Configurer les comportements simulés pour les repositories mockés
        ProductType productType = new ProductType();
        productType.setId(1);
        productType.setName("TestProductType");

        Mockito.when(productTypeRepository.findById(1)).thenReturn(Optional.of(productType));

        Product product = new Product();
        product.setId(1);
        product.setName("TestProduct");
        product.setCreatedDate(LocalDateTime.now());

        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
    }

    @Test
    public void testAddProduct() {
        // Créer un objet ProductWithType simulé pour le test
        ProductWithType productWithType = new ProductWithType();
        productWithType.setIdProductType(1);
        productWithType.setProductName("TestProduct");

        // Appeler la méthode de service pour ajouter un produit
        Product addedProduct = productServices.addProduct(productWithType);

        // Vérifier que le produit a été ajouté avec succès
        assertThat(addedProduct).isNotNull();
        assertThat(addedProduct.getName()).isEqualTo("TestProduct");
        assertThat(addedProduct.getProductType()).isNotNull();
        assertThat(addedProduct.getProductType().getName()).isEqualTo("TestProductType");
    }

    @Test
    public void testUpdateProduct() {
        // Créer un objet ProductWithType simulé pour le test
        ProductWithType productWithType = new ProductWithType();
        productWithType.setIdProductType(1);
        productWithType.setProductName("UpdatedTestProduct");

        // Appeler la méthode de service pour mettre à jour un produit
        Product updatedProduct = productServices.updateProduct(1, productWithType);

        // Vérifier que le produit a été mis à jour avec succès
        assertThat(updatedProduct).isNotNull();
        assertThat(updatedProduct.getName()).isEqualTo("UpdatedTestProduct");
        assertThat(updatedProduct.getProductType()).isNotNull();
        assertThat(updatedProduct.getProductType().getName()).isEqualTo("TestProductType");
    }

    // Vous pouvez ajouter d'autres tests pour les autres méthodes du service

}

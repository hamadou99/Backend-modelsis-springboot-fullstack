package com.backend.modelsis.services;

import com.backend.modelsis.entites.ProductType;
import com.backend.modelsis.repository.ProductTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ProductTypeServicesTest {

    @Autowired
    private ProductTypeServices productTypeServices;

    @MockBean
    private ProductTypeRepository productTypeRepository;

    @BeforeEach
    public void setUp() {
        // Configurer les comportements simulés pour le repository mocké
        ProductType productType = new ProductType();
        productType.setId(1);
        productType.setName("TestProductType");

        Mockito.when(productTypeRepository.findById(1)).thenReturn(Optional.of(productType));

        List<ProductType> productTypes = List.of(productType);
        Mockito.when(productTypeRepository.findAll()).thenReturn(productTypes);
    }

    @Test
    public void testAddProductType() {
        // Créer un objet ProductType simulé pour le test
        ProductType productType = new ProductType();
        productType.setName("NewProductType");

        // Appeler la méthode de service pour ajouter un type de produit
        ProductType addedProductType = productTypeServices.addProductType(productType);

        // Vérifier que le type de produit a été ajouté avec succès
        assertThat(addedProductType).isNotNull();
        assertThat(addedProductType.getName()).isEqualTo("NewProductType");
    }

    @Test
    public void testUpdateProductType() {
        // Créer un objet ProductType simulé pour le test
        ProductType productType = new ProductType();
        productType.setId(1);
        productType.setName("UpdatedProductType");

        // Appeler la méthode de service pour mettre à jour un type de produit
        ProductType updatedProductType = productTypeServices.updateProductType(1, productType);

        // Vérifier que le type de produit a été mis à jour avec succès
        assertThat(updatedProductType).isNotNull();
        assertThat(updatedProductType.getName()).isEqualTo("UpdatedProductType");
    }

    // Vous pouvez ajouter d'autres tests pour les autres méthodes du service

}

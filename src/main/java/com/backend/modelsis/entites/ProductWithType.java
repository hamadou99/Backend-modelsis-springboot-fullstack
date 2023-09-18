package com.backend.modelsis.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithType {
    private Integer idProductType;
    private String productName;
}

package org.rhydo.microecom.services;

import org.rhydo.microecom.dtos.ProductRequest;
import org.rhydo.microecom.dtos.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);
}

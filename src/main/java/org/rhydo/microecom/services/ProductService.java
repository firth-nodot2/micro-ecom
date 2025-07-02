package org.rhydo.microecom.services;

import org.rhydo.microecom.dtos.ProductRequest;
import org.rhydo.microecom.dtos.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    void deleteProduct(Long id);

    List<ProductResponse> searchProducts(String keyword);
}

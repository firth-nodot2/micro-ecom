package org.rhydo.microecom.repositories;

import org.rhydo.microecom.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

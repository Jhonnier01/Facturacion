package co.edu.poli.facturacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.poli.facturacion.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCase(String name);
}

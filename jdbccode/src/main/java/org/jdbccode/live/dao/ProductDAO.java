package org.jdbccode.live.dao;

import org.jdbccode.live.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    List<Product> findAll();

    Product find(int id);

    Product  save(Product product);

    boolean deleteById(int id);
/*
    Product create(Product product);
    Optional<Product> find(int id);
    List<Product> findAll();
    Product update(Product product);
    boolean delete(int id);
 */
}

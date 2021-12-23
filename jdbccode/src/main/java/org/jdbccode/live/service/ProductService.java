package org.jdbccode.live.service;

import org.jdbccode.live.dao.ProductDAO;
import org.jdbccode.live.model.Product;

import java.util.List;

public class ProductService {
    private ProductDAO productDAO;
    private static ProductService productService;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    public Product find(int id) {
        return productDAO.find(id);
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Product save(Product product) {
        return productDAO.save(product);
    }

    public boolean deletById(int id) {
        return productDAO.deleteById(id);
    }


}

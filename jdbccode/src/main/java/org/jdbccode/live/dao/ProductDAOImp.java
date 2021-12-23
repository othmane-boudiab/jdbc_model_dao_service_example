package org.jdbccode.live.dao;

import org.jdbccode.live.ConnectionDB;
import org.jdbccode.live.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDAOImp implements ProductDAO{
    @Override
    public List<Product> findAll() {
        Connection con = ConnectionDB.getConnection();
        if (con == null) {
            return null;
        }
        List<Product> products = new LinkedList<>();

        String query = "SELECT * FROM product";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("description"), resultSet.getDouble("prix"));
                products.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                con.close();
            }catch (SQLException c){
                c.printStackTrace();
            }
        }
        return products;
    }

    @Override
    public Product find(int id) {
        Connection con = ConnectionDB.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM product WHERE id = ?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("description"), resultSet.getDouble("prix"));
                return product;
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                con.close();
            }catch (SQLException c){
                c.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Product save(Product product) {
        Connection con = ConnectionDB.getConnection();
        if (con == null) {
            return null;
        }

        if (product.getId() > 0) { // update
            String query = "UPDATE product SET title = ?, description = ?, prix = ? WHERE id = ?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, product.getTitle());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setDouble(3, product.getPrix());
                preparedStatement.setInt(4, product.getId());
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                }catch (SQLException c){
                    c.printStackTrace();
                }
            }

        }else { // create
            String query = "INSERT INTO product (title, description, prix) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, product.getTitle());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setDouble(3, product.getPrix());
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                }catch (SQLException c){
                    c.printStackTrace();
                }
            }

        }
        return product;
    }

    @Override
    public boolean deleteById(int id) {
        Connection con = ConnectionDB.getConnection();
        if (con == null) {
            return false;
        }

        String query = "DELETE FROM product WHERE id = ?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                con.close();
            }catch (SQLException c){
                c.printStackTrace();
            }
        }
        return true;
    }
}

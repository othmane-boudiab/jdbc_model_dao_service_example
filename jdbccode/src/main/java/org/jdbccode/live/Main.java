package org.jdbccode.live;

import org.jdbccode.live.dao.ProductDAO;
import org.jdbccode.live.dao.ProductDAOImp;
import org.jdbccode.live.model.Product;
import org.jdbccode.live.service.ProductService;

import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String[] args){

        /*
        Connection con = ConnectionDB.getConnection();
        if(con == null) {
            System.out.println("con f");
        }else {
            System.out.println("con c");
        }

         */


          ProductDAO productDAO = new ProductDAOImp();
           Product product = new Product(5, "preduct24", "description product 24", 1.24d);
        // productDAO.save(product);

         // productDAO.findAll().forEach(System.out::println);
 //       Product pr = productDAO.find(2);
//        System.out.println(pr);
        // productDAO.deleteById(1);

        // forEach(System.out::println)
//          Product prod = new ProductService(productDAO).find(5);
//          System.out.println(prod);
//        List<Product> pro = new ProductService(productDAO).findAll();
//        pro.forEach(System.out::println);

         Product pr = new ProductService(productDAO).save(product);
         boolean p = new ProductService(productDAO).deletById(5);



    }
}
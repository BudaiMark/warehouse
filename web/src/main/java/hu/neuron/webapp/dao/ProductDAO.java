package hu.neuron.webapp.dao;

import hu.neuron.dto.Product;
import hu.neuron.webapp.database_connection.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;


public class ProductDAO {

    private Connection connection;

    public ProductDAO(){
        //Elkérjük a kapcsolatot.
        connection = DatabaseConnect.getConnection();
    }


    public List<Product> getAllProducts() {
        /*
        * Kiolvassuk az adatbázisból az összes terméket, beletesszük egy listába, majd visszatérünk vele.
        */
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product")){
            ResultSet rs = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while(rs.next()){
                Product temp = Product.builder()
                        .name(rs.getString("name"))
                        .category(rs.getString("category"))
                        .unit(rs.getLong("unit"))
                        .measure(rs.getLong("measure"))
                        .purchasePrice(rs.getLong("purchaseprice"))
                        .salePrice(rs.getLong("saleprice"))
                        .description(rs.getString("description"))
                        .build();
                products.add(temp);

            }

            return products;

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean insertProductTable(Product product){
        /*
        *  Beleszűrjuk a apraméterként kapott terméket az adatbázisba.
        */
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (name,category,unit,measure,purchaseprice,saleprice,description) VALUES(?,?,?,?,?,?,?)")){
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setLong(3, product.getUnit());
            preparedStatement.setLong(4, product.getMeasure());
            preparedStatement.setLong(5, product.getPurchasePrice());
            preparedStatement.setLong(6, product.getSalePrice());
            preparedStatement.setString(7, product.getDescription());
            preparedStatement.executeUpdate();

            return true;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

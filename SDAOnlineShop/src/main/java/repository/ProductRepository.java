package repository;

import model.Product;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public static List<Product> findAll() {
        List<Product> productList = new ArrayList<Product>();
        String selectAll = "SELECT * FROM product";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectAll);
            ResultSet rs = pstmt.executeQuery(selectAll);

            while(rs.next()) {

                int productId = rs.getInt(1);
                String type = rs.getString(2);
                String color = rs.getString(3);
                String size = rs.getString(4);
                String productCode = rs.getString(5);
                int price = rs.getInt(6);

                Product product = new Product(productId, type, color, size,productCode,price);
                productList.add(product);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productList;
    }

    public static Product findById(int id) {
        Product product = null;
        String selectById = "SELECT * FROM product where productId=?";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectById);
            //pstmt.setString(1,login.getAccountId());
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {

                int productId = rs.getInt(1);
                String type = rs.getString(2);
                String color = rs.getString(3);
                String size = rs.getString(4);
                String productCode = rs.getString(5);
                int price = rs.getInt(6);

                product = new Product(productId, type, color, size, productCode, price);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    public static void deleteProductById(int id) {
        String deleteById = "DELETE FROM product where productId=?";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(deleteById);
            pstmt.setInt(1,id);
            int deletedRecords = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void saveNewProduct(Product product) {
        String newProduct = "INSERT INTO Product VALUES(null,?,?,?,?,?)";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(newProduct);
            pstmt.setString(1,product.getType());
            pstmt.setString(2,product.getColor());
            pstmt.setString(3,product.getSize());
            pstmt.setString(4,product.getProductCode());
            pstmt.setInt(5,product.getPrice());

            int newRecords = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateProductById(int productId,Product product) {
        String updateProduct = "UPDATE Product SET color=?,size=?,productCode=?,price=? where productId=?";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(updateProduct);

            pstmt.setString(1,product.getColor());
            pstmt.setString(2,product.getSize());
            pstmt.setString(3,product.getProductCode());
            pstmt.setInt(4,product.getPrice());
            pstmt.setInt(5,productId);

            int newRecords = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int getLastProductId(){
        int productId = 0 ;
        String lastProductId = "SELECT MAX(productId) FROM product";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(lastProductId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                productId = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productId;
    }
}

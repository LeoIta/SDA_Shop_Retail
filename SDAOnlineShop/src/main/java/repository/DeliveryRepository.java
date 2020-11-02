package repository;
import model.Delivery;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRepository {
    public static List<Delivery> findAll() {
        List<Delivery> deliveryList = new ArrayList<Delivery>();
        String selectAll = "SELECT * FROM delivery";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectAll);
            ResultSet rs = pstmt.executeQuery(selectAll);

            while(rs.next()) {

                int deliveryId = rs.getInt(1);
                String name = rs.getString(2);
                int deliveryCost = rs.getInt(3);

                Delivery delivery = new Delivery(deliveryId, name, deliveryCost);
                deliveryList.add(delivery);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return deliveryList;
    }

    public static int getIdByName(String cpyName){
        int deliveryId = 0;
        String getId = "SELECT deliveryId FROM delivery where name=?";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(getId);
            pstmt.setString(1,cpyName);
            ResultSet rs = pstmt.executeQuery(getId);
            while(rs.next()) {
                deliveryId = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deliveryId;
    }

    public static Delivery findById(int id) {
        Delivery delivery = new Delivery();
        String selectById = "SELECT * FROM delivery where deliveryId=?";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectById);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery(selectById);
            while(rs.next()) {
                int deliveryId = rs.getInt(1);
                String name = rs.getString(2);
                int deliveryCost = rs.getInt(3);
                delivery = new Delivery(deliveryId, name, deliveryCost);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return delivery;
    }

    public static void deleteDeliveryById(int id) {
        String deleteById = "DELETE FROM delivery where deliveryId=?";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(deleteById);
            //pstmt.setString(1,delivery.getDeliveryId());
            pstmt.setInt(1,id);
            int deletedRecords = pstmt.executeUpdate(deleteById);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void saveNewLogin(Delivery delivery) {
        String newLogin = "INSERT INTO Login VALUES(null,?,?)";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(newLogin);
            pstmt.setString(1,delivery.getName());
            pstmt.setInt(2,delivery.getDeliveryCost());

            int newRecords = pstmt.executeUpdate(newLogin);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateLoginById(int id,Delivery delivery) {
        String updateDelivery = "UPDATE Login SET name=?,password=? where deliveryId=?";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(updateDelivery);

            pstmt.setString(1,delivery.getName());
            pstmt.setInt(2,delivery.getDeliveryCost());
            pstmt.setInt(3,id);

            int newRecords = pstmt.executeUpdate(updateDelivery);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
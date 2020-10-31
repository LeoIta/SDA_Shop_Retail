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
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectAll);
            ResultSet rs = pstmt.executeQuery(selectAll);
            
            while(rs.next()) {

                int deliveryId = rs.getInt(1);
                String name = rs.getString(2);
                int deliveryCost = rs.getString(3);
                
                Delivery delivery = new Delivery(deliverId, name, deliveryCost);
                deliveryList.add.(delivery);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
        }
        return deliveryList;}
    
    public static List<Delivery> findById(int Id) {
        List<Delivery> deliveryList = new ArrayList<Delivery>();
        String selectById = "SELECT * FROM delivery where deliveryId=?";

        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectById);
            pstmt.setString(1,Id);
            ResultSet rs = pstmt.executeQuery(selectById);
            while(rs.next()) {

                int deliveryId = rs.getInt(1);
                String name = rs.getString(2);
                int deliveryCost = rs.getString(3);

                Delivery delivery = new Delivery(deliverId, name, deliveryCost);
                deliveryList.add.(delivery);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}
        return deliveryList;}

    public static void deleteDeliveryById(int Id) {
        String deleteById = "DELETE FROM delivery where deliveryId=?";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(deleteById);
            pstmt.setString(1,delivery.getDeliveryId());
            int deletedRecords = pstmt.executeUpdate(deleteById);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}

    public static void saveNewLogin(Delivery delivery) {
        String newLogin = "INSERT INTO Login VALUES(null,?,?)";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(newLogin);
            pstmt.setString(1,delivery.getName());
            pstmt.setString(2,delivery.getDeliveryCost());

            int newRecords = pstmt.executeUpdate(newLogin);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}}

    public static void updateLoginById(int Id,Delivery delivery) {
        String updateDelivery = "UPDATE Login SET name=?,password=? where deliveryId=?";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(updateDelivery);
            
            pstmt.setString(1,delivery.getName());
            pstmt.setString(2,delivery.getDeliveryCost());
            pstmt.setString(3,Id);

            int newRecords = pstmt.executeUpdate(updateDelivery);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}
            
    }

  

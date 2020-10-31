package repository;

import model.Customer;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    public static List<Customer> findAll() {
        List<Customer> CustomerList = new ArrayList<Customer>();
        String selectAll = "SELECT * FROM Customer";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectAll);
            ResultSet rs = pstmt.executeQuery(selectAll);
            
            while(rs.next()) {

                int customerID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String mail = rs.getString(4);;
                String telephone = rs.getString(5);
                int addressID = rs.getInt(6);
                int accountID = rs.getInt(7);
                
                Customer customer = new Customer(firstName, lastName, mail, telephone, addressID);
                CustomerList.add(customer);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
        }
        return CustomerList;}
    
    public static List<Customer> findById(int Id) {
        List<Customer> CustomerList = new ArrayList<Customer>();
        String selectById = "SELECT * FROM Customer where customerID=?";

        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectById);
            pstmt.setString(1,customer.getCustomerId());
            ResultSet rs = pstmt.executeQuery(selectById);
            while(rs.next()) {

                int customerID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String mail = rs.getString(4);
                String telephone = rs.getString(5);
                int addressID = rs.getInt(6);
                int accountID = rs.getInt(7);

                Customer customer = new Customer(firstName, lastName, mail, telephone, addressID);
                CustomerList.add(customer);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}
        return CustomerList;}

    public static void deleteCustomerById(int Id) {
        String deleteById = "DELETE FROM Customer where customerID=?";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(deleteById);
            pstmt.setString(1,customer.getCustomerId());
            int deletedRecords = pstmt.executeUpdate(deleteById);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}

    public static void saveNewCustomer(Customer customer) {
        String newCustomer = "INSERT INTO Customer VALUES(null,?,?,?,?,?,?)";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(newCustomer);
            pstmt.setString(1,customer.getFirstName());
            pstmt.setString(2,customer.getLastName());
            pstmt.setString(3,customer.getMail());
            pstmt.setString(4,customer.getTelephone());
            pstmt.setString(5,customer.getAddressId());
            pstmt.setString(6,customer.getAccountId());

            int newRecords = pstmt.executeUpdate(newCustomer);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}

    public static void updateCustomerById(int Id,Customer customer) {
        String updateCustomer = "UPDATE Customer SET first_name=?, last_name=?,email=?,telephone=? where id=?";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(updateCustomer);
            
            pstmt.setString(1,customer.getFirstName());
            pstmt.setString(2,customer.getLastName());
            pstmt.setString(3,customer.getMail());
            pstmt.setString(4,customer.getTelephone());
            pstmt.setString(5,customer.getAccountId());

            int newRecords = pstmt.executeUpdate(updateCustomer);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}
       
}
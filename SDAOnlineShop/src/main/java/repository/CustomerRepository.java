package repository;

import model.Customer;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    public static List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<Customer>();
        String selectAll = "SELECT * FROM Customer";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectAll);
            ResultSet rs = pstmt.executeQuery();
            System.out.println(" we are connected to db");

            while(rs.next()) {

                int customerID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String mail = rs.getString(4);;
                String telephone = rs.getString(5);
                int addressID = rs.getInt(6);
                int accountID = rs.getInt(7);

                Customer customer = new Customer(firstName, lastName, mail, telephone, addressID,accountID);
                customerList.add(customer);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        }
        return customerList;
    }

    public static List<Customer> findById(int id) {
        List<Customer> customerList = new ArrayList<Customer>();
        String selectById = "SELECT * FROM Customer where customerId=?";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectById);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {

                int customerID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String mail = rs.getString(4);
                String telephone = rs.getString(5);
                int addressID = rs.getInt(6);
                int accountID = rs.getInt(7);

                Customer customer = new Customer(firstName, lastName, mail, telephone, addressID,accountID);
                customerList.add(customer);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    public static void deleteCustomerById(int id) {
        String deleteById = "DELETE FROM Customer where customerId=?";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(deleteById);
            pstmt.setInt(1,id);
            int deletedRecords = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}
    }

    public static void saveNewCustomer(Customer customer) {
        String newCustomer = "INSERT INTO Customer VALUES(null,?,?,?,?,?,?)";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(newCustomer);
            pstmt.setString(1,customer.getFirstName());
            pstmt.setString(2,customer.getLastName());
            pstmt.setString(3,customer.getMail());
            pstmt.setString(4,customer.getTelephone());
            pstmt.setInt(5,customer.getAddressId());
            pstmt.setInt(6,customer.getAccountId());

            int newRecords = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}

    public static void updateCustomerById(int Id,Customer customer) {
        String updateCustomer = "UPDATE Customer SET first_name=?, last_name=?,email=?,telephone=? where customerId=?";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(updateCustomer);

            pstmt.setString(1,customer.getFirstName());
            pstmt.setString(2,customer.getLastName());
            pstmt.setString(3,customer.getMail());
            pstmt.setString(4,customer.getTelephone());
            pstmt.setInt(5,customer.getAccountId());

            int newRecords = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}
    }

    /// Added

    public static int getLastCustomerId(){
        int customerId = 0 ;
        String lastCustomerId = "SELECT MAX(customerId) FROM customer";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(lastCustomerId);
            ResultSet rs = pstmt.executeQuery(lastCustomerId);
            while(rs.next()) {
                customerId = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerId;
    }

    public static Customer getLastAddedCustomer(){
        //SELECT * FROM customer WHERE customerId=(SELECT MAX(customerId) FROM customer);
        String getLastCustomer = "SELECT * FROM customer WHERE customerId=(SELECT MAX(customerId) FROM customer)";
        Customer customer = new Customer();
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(getLastCustomer);
            ResultSet rs = pstmt.executeQuery(getLastCustomer);
            while(rs.next()) {

                int customerID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String mail = rs.getString(4);
                String telephone = rs.getString(5);
                int addressID = rs.getInt(6);
                int accountId = rs.getInt(7);

                customer = new Customer(customerID, firstName, lastName, mail, telephone, addressID,accountId);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    public static List<Customer> findByAccountId(int accountId) {
        List<Customer> customerList = new ArrayList<Customer>();
        String selectById = "SELECT * FROM Customer where accountId=?";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectById);
            pstmt.setInt(1,accountId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {

                int customerID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String mail = rs.getString(4);
                String telephone = rs.getString(5);
                int addressID = rs.getInt(6);

                Customer customer = new Customer(customerID, firstName, lastName, mail, telephone, addressID,accountId);
                customerList.add(customer);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }


    /*public void display(){

        try {
            if(resultSet.isBeforeFirst()){resultSet.first();}
            else if(resultSet.isAfterLast()){resultSet.last();}

            String str1 = (resultSet.getString(1));
            String str2 = (resultSet.getString(2));
            String str3 = (resultSet.getString(3));
            String str4 = (resultSet.getString(4));

            System.out.println(str1 +" | "+ str2 + " | " + str3 +" | " +str4);

        }catch (SQLException e){
            System.out.println( e.getMessage());
        }
    }*/

}
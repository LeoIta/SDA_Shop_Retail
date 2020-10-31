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
            Statement pstmt = connection.prepareStatement(selectAll);
            ResultSet rs = pstmt.executeQuery(selectAll);
            while(rs.next()) {

                int customerID = rs.getInt(1);
                String firstName = rs.getString(2);;
                String lastName = rs.getString(3);;
                String mail = rs.getString(4);;
                String telephone = rs.getString(5);;
                int addressID = rs.getInt(6);;
                int accountID = rs.getInt(7);;
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
}

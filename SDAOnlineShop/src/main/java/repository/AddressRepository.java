package repository;
import model.Address;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AddressRepository {

    public static List<Address> findAll() {
        List<Address> addressList = new ArrayList<Address>();
        String selectAll = "SELECT * FROM address";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectAll);
            ResultSet rs = pstmt.executeQuery(selectAll);
            
            while(rs.next()) {

                int addressId = rs.getInt(1);
                String country = rs.getString(2);
                String city = rs.getString(3);
                String postalCode = rs.getString(4);
                
                Address address = new Address(country, city, postalCode);
                addressList.add(address);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
        }
        return addressList;}
    
    public static List<Address> findById(int Id) {
        List<Address> addressList = new ArrayList<Address>();
        String selectById = "SELECT * FROM address where addressId=?";

        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectById);
            pstmt.setString(1,Id);
            ResultSet rs = pstmt.executeQuery(selectById);
            while(rs.next()) {

                int addressId = rs.getInt(1);
                String country = rs.getString(2);
                String city = rs.getString(3);
                String postalCode = rs.getString(4);

                Address address = new Address(country, city, postalCode);
                addressList.add(address);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}
        return addressList;}

    public static void deleteAddressById(int Id) {
        String deleteById = "DELETE FROM address where addressId=?";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(deleteById);
            pstmt.setString(1,Id);
            int deletedRecords = pstmt.executeUpdate(deleteById);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}

    public static void saveNewAddress(Address address) {
        String newAddress = "INSERT INTO Address VALUES(null,?,?,?)";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(newAddress);
            pstmt.setString(1,address.getCountry());
            pstmt.setString(2,address.getCity());
            pstmt.setString(3,address.getPostalCode());

            int newRecords = pstmt.executeUpdate(newAddress);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}

    public static void updateAddressById(int Id,Address address) {
        String updateAddress = "UPDATE Address SET Country=?, City=?,PostalCode=? where addressId=?";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(updateAddress);
            
            pstmt.setString(1,address.getCountry());
            pstmt.setString(2,address.getCity());
            pstmt.setString(3,address.getPostalCode());
            pstmt.setString(4,Id);

            int newRecords = pstmt.executeUpdate(updateAddress);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}
        
}
}
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
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectAll);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {

                int addressId = rs.getInt(1);
                String country = rs.getString(2);
                String city = rs.getString(3);
                String postalCode = rs.getString(4);
                String street = rs.getString(5);

                Address address = new Address(addressId, country, city, postalCode,street);
                addressList.add(address);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return addressList;
    }

    public static int getLastAddressId(){
        int addressId = 0 ;
        String lastAddressId = "SELECT MAX(addressId) FROM address";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(lastAddressId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                addressId = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return addressId;
    }

    public static Address getLastAddedAddress(){
        Address address =new Address();
        String getLastAddress = "SELECT * FROM address WHERE addressId=(SELECT MAX(addressId) FROM address)";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(getLastAddress);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {

                int addressId = rs.getInt(1);
                String country = rs.getString(2);
                String city = rs.getString(3);
                String postalCode = rs.getString(4);
                String street = rs.getString(5);
                address = new Address(addressId,country, city, postalCode,street);

            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return address;
    }

    public static Address findById(int id) {
        Address address = new Address();
        String selectById = "SELECT * FROM address where addressId=?";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectById);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {

                int addressId = rs.getInt(1);
                String country = rs.getString(2);
                String city = rs.getString(3);
                String postalCode = rs.getString(4);
                String street = rs.getString(5);

                address  = new Address(addressId,country, city, postalCode,street);

            }
            rs.close();
//            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return address;
    }

    public static void deleteAddressById(int id) {
        String deleteById = "DELETE FROM address where addressId=?";
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

    public static void saveNewAddress(Address address) {
        String newAddress = "INSERT INTO Address VALUES(null,?,?,?,?)";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(newAddress);
            pstmt.setString(1,address.getCountry());
            pstmt.setString(2,address.getCity());
            pstmt.setString(3,address.getPostalCode());
            pstmt.setString(4,address.getStreet());

            int newRecords = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateAddressById(int Id,Address address) {
        String updateAddress = "UPDATE Address SET Country=?, City=?,PostalCode=?, Street=? where addressId=?";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(updateAddress);

            pstmt.setString(1,address.getCountry());
            pstmt.setString(2,address.getCity());
            pstmt.setString(3,address.getPostalCode());
            pstmt.setString(4,address.getStreet());
            pstmt.setInt(5,Id);

            int newRecords = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

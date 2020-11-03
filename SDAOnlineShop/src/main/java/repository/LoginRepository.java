package repository;
import model.Address;
import model.Login;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class LoginRepository {

    public static List<Login> findAll() {
        List<Login> loginList = new ArrayList<Login>();
        String selectAll = "SELECT * FROM login";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectAll);
            ResultSet rs = pstmt.executeQuery(selectAll);

            while(rs.next()) {

                int accountId = rs.getInt(1);
                String userName = rs.getString(2);
                String password = rs.getString(3);

                Login login = new Login(accountId, userName, password);
                loginList.add(login);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return loginList;
    }

    public static List<Login> findById(int id) {
        List<Login> loginList = new ArrayList<Login>();
        String selectById = "SELECT * FROM login where accountId=?";

        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectById);
            //pstmt.setString(1,login.getAccountId());
            pstmt.setString(1,""+id+"");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {

                int accountId = rs.getInt(1);
                String userName = rs.getString(2);
                String password = rs.getString(3);

                Login login = new Login(accountId, userName, password);
                loginList.add(login);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}
        return loginList;
    }

    //// added
    public static int getLastLoginId(){
        int accountId = 0 ;
        String lastAccountId = "SELECT MAX(accountId) FROM login";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(lastAccountId);
            ResultSet rs = pstmt.executeQuery(lastAccountId);
            while(rs.next()) {
                accountId = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accountId;
    }

    public  static Login getLastAddedLogin(){
        Login login = new Login();
        String getLastLogin = "SELECT * FROM login WHERE accountId=(SELECT MAX(accountId) FROM login)";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(getLastLogin);
            ResultSet rs = pstmt.executeQuery(getLastLogin);
            while(rs.next()) {

                int accountId = rs.getInt(1);
                String userName = rs.getString(2);
                String password = rs.getString(3);

                login = new Login(accountId, userName, password);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return login;
    }

    public static List<Login> findAccountId(String userName, String password) {
        List<Login> loginList = new ArrayList<Login>();
        String selectById = "SELECT accountId FROM login where userName=? and password=?";

        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectById);
            //pstmt.setString(1,login.getAccountId());
            pstmt.setString(1,userName);
            pstmt.setString(2,password);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {

                int accountId = rs.getInt(1);

                Login login = new Login(accountId, userName, password);
                loginList.add(login);
           }

            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return loginList;
    }


    ////

    public static void deleteLoginById(int id) {
        String deleteById = "DELETE FROM login where accountId=?";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(deleteById);
            //pstmt.setString(1,login.getAccountId());
            pstmt.setString(1,Integer.toString(id));
            int deletedRecords = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}

    public static void saveNewLogin(Login login) {
        String newLogin = "INSERT INTO Login VALUES(null,?,?)";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(newLogin);
            pstmt.setString(1,login.getUserName());
            pstmt.setString(2,login.getPassword());

            int newRecords = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateLoginById(int Id,Login login) {
        String updateLogin = "UPDATE Login SET userName=?,password=? where accountId=?";
        try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(updateLogin);

            pstmt.setString(1,login.getUserName());
            pstmt.setString(2,login.getPassword());
            pstmt.setInt(3,Id);

            int newRecords = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

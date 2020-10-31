package repository;
import model.Login;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class LoginRepository {

    public static List<Login> findAll() {
        List<Login> loginList = new ArrayList<Login>();
        String selectAll = "SELECT * FROM login";
        {try {
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
        }
        return loginList;}
    
    public static List<Login> findById(int Id) {
        List<Login> loginList = new ArrayList<Login>();
        String selectById = "SELECT * FROM login where accountId=?";

        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(selectById);
            pstmt.setString(1,login.getAccountId());
            ResultSet rs = pstmt.executeQuery(selectById);
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
        return loginList;}

    public static void deleteLoginById(int Id) {
        String deleteById = "DELETE FROM login where accountId=?";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(deleteById);
            pstmt.setString(1,login.getAccountId());
            int deletedRecords = pstmt.executeUpdate(deleteById);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}

    public static void saveNewLogin(Login Login) {
        String newLogin = "INSERT INTO Login VALUES(null,?,?)";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(newLogin);
            pstmt.setString(1,login.getUserName());
            pstmt.setString(2,login.getPassword());

            int newRecords = pstmt.executeUpdate(newLogin);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}

    public static void updateLoginById(int Id,Login login) {
        String updateLogin = "UPDATE Login SET userName=?,password=? where accountId=?";
        {try {
            Connection connection = DBUtil.newConnection();
            PreparedStatement pstmt = connection.prepareStatement(updateLogin);
            
            pstmt.setString(1,login.getUserName());
            pstmt.setString(2,login.getPassword());

            int newRecords = pstmt.executeUpdate(updateLogin);
            pstmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}
        
}
}
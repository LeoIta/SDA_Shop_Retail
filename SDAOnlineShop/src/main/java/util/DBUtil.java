package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    //public static final String DATABASE_HOST = "jdbc:mysql://localhost:3306/sda_online_shop?serverTimezone=UTC";
    public static final String DATABASE_HOST = "jdbc:mysql://localhost:3306/sda_online_shop_reduced?serverTimezone=UTC";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "root";

    public static Connection newConnection() {
    Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    DATABASE_HOST,
                    DATABASE_USERNAME,
                    DATABASE_PASSWORD);
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    return connection;}
}
package db.basicLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/taxReports";
    private static final String login = "root";
    private static final String password = "root";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(DB_URL, login, password);
            System.out.println("Connection successful.");
        } catch (ClassNotFoundException e) {
            //throw new JDBCConnectionException("Can't load database driver.", e);
            System.out.println("Can't load database driver. " + e);
        } catch (SQLException e) {
            //throw new JDBCConnectionException("Can't connect to database.", e);
            System.out.println("Can't connect to database. " + e);
        }
        return conn;
    }
}

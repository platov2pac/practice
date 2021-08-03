package storage;

import dto.User;

import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;


public class UserStorage {

    private static UserStorage instance = new UserStorage();
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/myapp";
    private static final String USER = "postgres";
    private static final String PASS = "root";
    private Connection connection=null;

    private UserStorage(){}
    public static UserStorage getInstance() {
        return instance;
    }

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return null;
        }

        try {
            connection = DriverManager.getConnection(DB_URL,USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}

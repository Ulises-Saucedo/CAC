package org.codoacodo.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public String driver = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cac", "root", "");
        } catch(SQLException error) {
            System.out.println("Error: " + error);
        }

        return connection;
    }
}

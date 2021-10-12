package ir.maktab.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseAccess {
    private Connection connection=null;

    public DataBaseAccess() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/taxi_online", "root", "zo60na84");


    }

    public Connection getConnection() {
        return connection;
    }

}

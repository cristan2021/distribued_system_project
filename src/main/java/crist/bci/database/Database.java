package crist.bci.database;

import java.sql.*;


public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/bci";
    private static final String user = "moud";
    private static final String password = "Daniel3195?";
    private Connection connection;

    public Database() throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
        //this.connection = DriverManager.getConnection("jdbc:sqlite:bci.sqlite");
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }
}
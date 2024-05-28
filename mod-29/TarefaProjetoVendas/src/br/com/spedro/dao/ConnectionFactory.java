package br.com.spedro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection connection;

    public ConnectionFactory() {

    }

    public static Connection getConnection() throws SQLException {
        if(connection == null){
            connection = initConnection();
            return connection;
        } else if (connection.isClosed()){
            connection = initConnection();
            return connection;
        } else {
            return connection;
        }
    }

    private static Connection initConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/db_store", "pedrohsr", "admin");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}

package net.golovach.quiz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLExample_4 {
    public static final String JDBC_URL
            = "jdbc:mysql://127.0.0.1:3306/quiz_db?user=username&password=password";

    public static void main(String[] args) throws SQLException {        
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            System.out.println("conn = " + conn);
        }
    }
}

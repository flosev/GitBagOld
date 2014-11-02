package net.golovach.quiz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLExample_3A {
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db?user=username&password=password";

    public static void main(String[] args) throws SQLException {
//        System.out.println(DriverManager.getDriver("ABC"));
        System.out.println(DriverManager.getDriver(JDBC_URL));
        Connection connection = DriverManager.getConnection(JDBC_URL);
        System.out.println(connection);
    }
}



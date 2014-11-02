package net.golovach.quiz.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLExample_2 {
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db?user=username&password=password";

    public static void main(String[] args) throws SQLException {
//        com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
//        try (java.sql.Connection conn = driver.connect(JDBC_URL, new Properties())) {
//            System.out.println("conn = " + conn);
//        }
    }
}

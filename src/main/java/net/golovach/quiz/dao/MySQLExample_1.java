package net.golovach.quiz.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLExample_1 {
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db";
    public static final String LOGIN = "username";
    public static final String PASSWORD = "password";

    public static void main(String[] args) throws SQLException {
//        Driver driver = new com.mysql.jdbc.Driver();
//
//        System.out.println(driver.acceptsURL("abc"));
//        System.out.println(driver.acceptsURL(JDBC_URL));
//
//        Properties properties = new Properties();
//        properties.put("user", LOGIN);
//        properties.put("password", PASSWORD);
//        Connection conn = driver.connect(JDBC_URL, properties);
//        conn.close();
    }
}

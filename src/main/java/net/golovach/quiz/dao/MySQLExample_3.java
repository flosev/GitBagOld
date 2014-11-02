package net.golovach.quiz.dao;

//import sun.jdbc.odbc.JdbcOdbcDriver;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class MySQLExample_3 {
    public static void main(String[] args) throws SQLException {
        Enumeration<Driver> iter = DriverManager.getDrivers();
        while (iter.hasMoreElements()) {
            Driver driver = iter.nextElement();
            System.out.println("driver = " + driver);
        }
    }
}

package net.golovach.quiz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLExample_7 {
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db?user=username&password=password";

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Users(id, login, name, password) VALUES (?, ?, ?, ?)");
            for (int k = 0; k < 3; k++) {
                stmt.setInt(1, (int) System.nanoTime());
                stmt.setString(2, "" + System.nanoTime());
                stmt.setString(3, "" + System.nanoTime());
                stmt.setString(4, "" + System.nanoTime());
                int howMuchRowsAffected = stmt.executeUpdate();
                System.out.println(howMuchRowsAffected + " rows affected");
            }
            conn.commit();
        }
    }
}

package net.golovach.quiz.dao;

import java.sql.*;

public class MySQLExample_8 {
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db?user=username&password=password";

    public static void main(String[] args) throws SQLException {
        Connection conn0 = null;
        try (Connection conn = (conn0 = DriverManager.getConnection(JDBC_URL))) {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            conn.setHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT);
            conn.setReadOnly(true);
            Statement stmt = conn.createStatement();
            stmt.setFetchDirection(ResultSet.FETCH_FORWARD);
            ResultSet rs = stmt.executeQuery("SELECT id FROM Users");
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
            }
            conn.commit();
        } catch (SQLException e) {
            if (conn0 != null) {
                conn0.rollback();
            }
        }
    }
}


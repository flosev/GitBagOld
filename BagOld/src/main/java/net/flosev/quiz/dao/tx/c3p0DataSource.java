package net.flosev.quiz.dao.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import static net.flosev.util.ClassName.getCurrentClassName;

public class c3p0DataSource extends BaseDataSource implements DataSource {
    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    private String driverClass;
    private String jdbcUrl;
    private String user;
    private String password;
    private ComboPooledDataSource dataSource;

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(jdbcUrl + "?user=" + user + "&password=" + password);
        logger.debug("call newConnection() from c3p0 pool");
        return dataSource.getConnection();
    }

    public void init() throws SQLException {
        try {
            dataSource = new ComboPooledDataSource();
            // db
            dataSource.setDriverClass(driverClass);
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUser(user);
            dataSource.setPassword(password);
            // connection pool
            dataSource.setMinPoolSize(4);
            dataSource.setAcquireIncrement(4);
            dataSource.setMaxPoolSize(20);
            dataSource.setMaxConnectionAge(10 * 60 * 1000); //todo: millis
            dataSource.setMaxIdleTime(1 * 60 * 1000); //todo: millis
            dataSource.setIdleConnectionTestPeriod(1 * 60 * 1000); //todo: millis
            // statement pool
            dataSource.setMaxStatements(180);
            dataSource.setMaxStatementsPerConnection(10);
        } catch (PropertyVetoException e) {
            throw new SQLException("Exception during configuring c3p0", e);
        }
    }

    public void close() throws SQLException {
        logger.debug("close c3p0 connection factory");
        dataSource.close();
    }
}


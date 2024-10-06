package com.leandroleitedev.jdbc.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public abstract class AbstractDataBaseConnection {

    protected Connection connection;

    protected abstract String getUrl();

    protected abstract String getUser();

    protected abstract String getPassword();

    public Connection getConnection() {
        try {
            if (Objects.isNull(connection) || connection.isClosed()) {
                connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

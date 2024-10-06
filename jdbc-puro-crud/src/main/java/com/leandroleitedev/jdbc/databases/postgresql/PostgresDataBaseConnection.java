package com.leandroleitedev.jdbc.databases.postgresql;

import com.leandroleitedev.jdbc.databases.AbstractDataBaseConnection;

import java.sql.SQLException;
import java.util.Objects;

public class PostgresDataBaseConnection extends AbstractDataBaseConnection {

    private static PostgresDataBaseConnection instance;

    private PostgresDataBaseConnection() throws SQLException {
        super();
    }

    public static AbstractDataBaseConnection getInstance() {
        if (Objects.isNull(instance)) {
            try {
                instance = new PostgresDataBaseConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    @Override
    protected String getUrl() {
        return "jdbc:postgresql://localhost:5432/mentoria-devs-fora-da-curva";
    }

    @Override
    protected String getUser() {
        return "postgres";
    }

    @Override
    protected String getPassword() {
        return "1234";
    }

}

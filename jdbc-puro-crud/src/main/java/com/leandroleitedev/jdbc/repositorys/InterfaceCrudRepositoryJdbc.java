package com.leandroleitedev.jdbc.repositorys;

import com.leandroleitedev.jdbc.entitys.Address;
import com.leandroleitedev.jdbc.entitys.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceCrudRepositoryJdbc <T, U> {

    Long save(String sql, T t) throws SQLException;

    void saveList(String sql, List<U> u, Long id);

    void delete(String sql, Long id);

    ResultSet findById(String sql, Long id);

    ResultSet findAll(String sql);
}

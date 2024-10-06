package com.leandroleitedev.jdbc.repositorys.impl;

import com.leandroleitedev.jdbc.databases.AbstractDataBaseConnection;
import com.leandroleitedev.jdbc.databases.postgresql.PostgresDataBaseConnection;
import com.leandroleitedev.jdbc.entitys.Address;
import com.leandroleitedev.jdbc.entitys.Customer;
import com.leandroleitedev.jdbc.repositorys.InterfaceCrudRepositoryJdbc;

import java.sql.*;
import java.util.List;

public class CrudRepositoryJdbc  {

    private static final AbstractDataBaseConnection DATA_BASE_CONNECTION = PostgresDataBaseConnection.getInstance();
    private static final Connection CONNECTION = DATA_BASE_CONNECTION.getConnection();


    public Long save(String sql, Customer customer) {
        try {
            PreparedStatement psCustomer = CONNECTION.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psCustomer.setString(1, customer.getName());
            psCustomer.setString(2, customer.getEmail());
            psCustomer.executeUpdate();

            // Obter o ResultSet e chamar next()
            ResultSet rs = psCustomer.getGeneratedKeys();
            if (rs.next()) {  // Aqui chamamos next() para mover o cursor
                Long customerId = rs.getLong(1);
                System.out.println("Inserted customer with ID " + customerId);
                return customerId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void saveList(String sql, List<Address> addressList, Long id) {

        try {
            PreparedStatement psAddress = CONNECTION.prepareStatement(sql);

            for (Address address : addressList) {
                psAddress.setString(1, address.getStreet());
                psAddress.setString(2, address.getNeighborhood());
                psAddress.setString(3, address.getState());
                psAddress.setLong(4, id);
                psAddress.addBatch();
            }
            psAddress.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String sql, Long id) {

        try (PreparedStatement stmt = CONNECTION.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int customerDeleted = stmt.executeUpdate();
            System.out.println("Deleted " + customerDeleted + " customer(s) with ID " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete customer", e);
        }
    }

    public ResultSet findById(String sql, Long id) {

        ResultSet rs = null;

        try {
            PreparedStatement stmt = CONNECTION.prepareStatement(sql);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet findAll(String sql) {
        ResultSet rs = null;
        try {
            PreparedStatement stmt = CONNECTION.prepareStatement(sql);
            rs = stmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
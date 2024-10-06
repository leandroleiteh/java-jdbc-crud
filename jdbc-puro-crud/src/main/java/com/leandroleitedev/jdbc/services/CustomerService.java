package com.leandroleitedev.jdbc.services;

import com.leandroleitedev.jdbc.entitys.Address;
import com.leandroleitedev.jdbc.entitys.Customer;
import com.leandroleitedev.jdbc.repositorys.impl.CrudRepositoryJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CustomerService {

    CrudRepositoryJdbc crudRepository = new CrudRepositoryJdbc();


    public void saveCustomer(Customer customer, List<Address> addressList) throws SQLException {
        String sqlCustomer = "INSERT INTO Customer (name, email) VALUES (?, ?)";
        String sqlAddress = "INSERT INTO Address (street, neighborhood, state, customer_id) VALUES (?, ?, ?, ?)";

        Long id = crudRepository.save(sqlCustomer, customer);
        crudRepository.saveList(sqlAddress, addressList, id);

    }

//    public void updateCustomer(Long id) {
//        String sql = "UPDATE Customer SET name = ?, email = ? WHERE id = ?";
//
//        crudRepository.update(sql, id);
//
//        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
//            ps.setString(1, customer.getName());
//            ps.setString(2, customer.getEmail());
//            ps.setLong(3, customer.getId());
//
//            int rowsAffected = ps.executeUpdate();
//            System.out.println("Updated " + rowsAffected + " customer(s) with ID " + customer.getId());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void deleteCustomer(Long id) {
        String sql = "DELETE FROM Customer WHERE id = ?";
        crudRepository.delete(sql, id);
    }

    public Customer findCustomerForId(Long id) throws SQLException {
        String sql = "SELECT c.id AS customer_id, c.name, c.email, a.id AS address_id, a.street, a.neighborhood, a.state " +
                "FROM Customer c " +
                "JOIN Address a ON c.id = a.customer_id " +
                "WHERE c.id = ?";

        ResultSet rs = crudRepository.findById(sql, id);

        if (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getLong("customer_id"));
            customer.setName(rs.getString("name"));
            customer.setEmail(rs.getString("email"));

            Set<Address> addresses = new HashSet<>();
            do {
                Address address = new Address();
                address.setId(rs.getLong("address_id"));
                address.setStreet(rs.getString("street"));
                address.setNeighborhood(rs.getString("neighborhood"));
                address.setState(rs.getString("state"));
                addresses.add(address);
            } while (rs.next());

            customer.setAddresses(addresses);
            return customer;
        }

        return null;
    }

    public List<Customer> allCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Map<Long, Customer> customerMap = new HashMap<>();

        String sql = "SELECT c.id AS customer_id, c.name, c.email, " + "a.id AS address_id, a.street, a.neighborhood, a.state " + "FROM Customer c " + "LEFT JOIN Address a ON c.id = a.customer_id";

        ResultSet rs = crudRepository.findAll(sql);

        while (rs.next()) {
            Long customerId = rs.getLong("customer_id");
            Customer customer = customerMap.get(customerId);

            if (Objects.isNull(customer)) {
                customer = new Customer();
                customer.setId(customerId);
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setAddresses(new HashSet<>());
                customerMap.put(customerId, customer);
                customers.add(customer);
            }

            long addressId = rs.getLong("address_id");
            if (addressId != 0) {
                Address address = new Address();
                address.setId(addressId);
                address.setStreet(rs.getString("street"));
                address.setNeighborhood(rs.getString("neighborhood"));
                address.setState(rs.getString("state"));
                address.setCustomer_Id(customerId);
                customer.getAddresses().add(address);
            }
            return customers;
        }

        return customers;

    }
}




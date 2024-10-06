package com.leandroleitedev.jdbc.entitys;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class Customer implements Serializable {

    private Long id;
    private String name;
    private String email;
    private Set<Address> addresses;

    public Customer() {
    }

    public Customer(Long id, String name, String email, Set<Address> addresses) {
        this.id = new Random().nextLong();
        this.name = name;
        this.email = email;
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append(String.format("  \"id\": %d,\n", id));
        sb.append(String.format("  \"name\": \"%s\",\n", name));
        sb.append(String.format("  \"email\": \"%s\",\n", email));
        sb.append("  \"addresses\": [\n");
        for (Address address : addresses) {
            sb.append("    ").append(address.toString().replace("\n", "\n    ")).append(",\n");
        }
        if (!addresses.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the last comma
            sb.append("\n");
        }
        sb.append("  ]\n");
        sb.append("}");
        return sb.toString();
    }
}

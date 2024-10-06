package com.leandroleitedev.jdbc.entitys;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Address implements Serializable {

    private Long id;
    private String street;
    private String neighborhood;
    private String state;
    private Long customer_Id;

    public Address() {
    }

    public Address(Long id, String street, String neighborhood, String state, Long customer_Id) {
        this.id = new Random().nextLong();
        this.street = street;
        this.neighborhood = neighborhood;
        this.state = state;
        this.customer_Id = customer_Id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(Long customer_Id) {
        this.customer_Id = customer_Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format(
                "{\n" +
                        "  \"id\": %d,\n" +
                        "  \"street\": \"%s\",\n" +
                        "  \"neighborhood\": \"%s\",\n" +
                        "  \"state\": \"%s\",\n" +
                        "  \"customerId\": %d\n" +
                        "}",
                id, street, neighborhood, state, customer_Id
        );
    }
}

package com.vs.customer.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomersDTO {
    List<CustomerDTO> customers = new ArrayList<>();

    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    public void addCustomer(CustomerDTO customer) {
        customers.add(customer);
    }
}

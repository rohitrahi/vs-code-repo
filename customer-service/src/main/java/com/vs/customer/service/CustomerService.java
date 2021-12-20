package com.vs.customer.service;

import com.vs.customer.dto.CustomerDTO;
import com.vs.customer.dto.CustomerDetailsDTO;
import com.vs.customer.repository.CustomerSodaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService  {
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    CustomerSodaRepository repository;

    public CustomerDTO getCustomerById(String id) throws Throwable {
        return repository.find(id);
    }

    public CustomerDetailsDTO getCustomerDetails(String id) throws Throwable {
        CustomerDTO customerDTO = getCustomerById(id);
        CustomerDetailsDTO retVal = new CustomerDetailsDTO();
        retVal.setId(customerDTO.getId());
        retVal.setCustomerName(customerDTO.getCustomerName());
        return retVal;
    }

    public CustomerDTO saveCustomer(CustomerDTO customer) {
        CustomerDTO retVal = new CustomerDTO();
        try {
            customer.setPassword(bcryptEncoder.encode(customer.getPassword()));
            retVal = repository.create(customer);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return retVal;
    }

}

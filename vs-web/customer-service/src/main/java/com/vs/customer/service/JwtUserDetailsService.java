package com.vs.customer.service;

import com.vs.customer.dto.CustomerDTO;
import com.vs.customer.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	CustomerService customerService;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		CustomerDTO customerDTO = null;

		try {
			customerDTO = customerService.getCustomerById(id);
		} catch (Throwable e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
		return new org.springframework.security.core.userdetails.User(customerDTO.getId(), customerDTO.getPassword(),
				new ArrayList<>());
	}

}
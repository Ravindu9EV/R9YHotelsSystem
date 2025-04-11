package edu.icet.service;

import edu.icet.dto.CustomerDTO;
import edu.icet.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<CustomerDTO> saveCustomer(CustomerDTO customerDTO);
    ResponseEntity<CustomerDTO> editCustomer(CustomerDTO customerDTO);
    ResponseEntity<String> deleteCustomerById(Long id);
    ResponseEntity<String> deleteCustomerByUserName(String username);
    ResponseEntity<CustomerDTO> findById(Long id);
    ResponseEntity<CustomerDTO> findByUsername(String username);
    List<CustomerDTO> getAll();
}

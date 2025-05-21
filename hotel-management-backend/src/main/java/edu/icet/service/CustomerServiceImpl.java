package edu.icet.service;

import edu.icet.dto.CustomerDTO;
import edu.icet.entity.Customer;
import edu.icet.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerRepo repo;
    @Override
    public ResponseEntity<CustomerDTO> saveCustomer(CustomerDTO customerDTO) {
        try{

           // Customer customer=mapper.map(customerDTO,Customer.class);
            Customer customer=new Customer(null, customerDTO.getUsername(), customerDTO.getPassword(), customerDTO.getEmail(), customerDTO.getPhone(), customerDTO.getAddress(), customerDTO.getAge());
            log.info(""+customerDTO);
            if(customerDTO.getUsername()==null || customerDTO.getUsername().isEmpty() || customerDTO.getEmail()==null ||customerDTO.getEmail().isEmpty() || customerDTO.getPassword()==null || customerDTO.getPassword().isEmpty() ){
                log.info("One field is Null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

            }

            System.out.println("Hi"+customer);
            customer=this.repo.save(customer);
            customerDTO.setId(customer.getId());
            System.out.println("SAved"+customer);
            return ResponseEntity.ok(customerDTO);
        }catch (Exception e) {
            log.info(String.valueOf(customerDTO));
            log.info(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Override
    public ResponseEntity<CustomerDTO> editCustomer(CustomerDTO customerDTO) {
        try{
            if(customerDTO.getUsername()==null || customerDTO.getUsername().isEmpty() || customerDTO.getEmail()==null || customerDTO.getEmail().isEmpty() || customerDTO.getPassword()==null || customerDTO.getPassword().isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Customer customer=repo.findById(customerDTO.getId()).get();
            customer=mapper.map(customerDTO, Customer.class);
            repo.save(customer);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<String> deleteCustomerById(Long id) {
        try{
            if(id==null || id<=0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Optional<Customer> customer=repo.findById(id);
            repo.delete(customer.get());

            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.info(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<String> deleteCustomerByUserName(String username) {
        try {
            if(username==null || username.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Optional<Customer> custom=repo.findByUsername(username);
            log.info(custom.toString());
            repo.delete(custom.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.info(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Override
    public ResponseEntity<CustomerDTO> findById(Long id) {
        try {
            if(id<=0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Customer customer=repo.findById(id).get();
            return ResponseEntity.ok(mapper.map(customer,CustomerDTO.class));
        }catch (Exception e){
            log.info(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<CustomerDTO> findByUsername(String username) {
        try{
            if(username.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            log.info(repo.findByUsername(username).toString());
            return ResponseEntity.ok(mapper.map(repo.findByUsername(username).get(),CustomerDTO.class));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<CustomerDTO> customerDTOS=new ArrayList<>();
        for(Customer cust:repo.findAll()){
            if(cust!=null){
                customerDTOS.add(mapper.map(cust,CustomerDTO.class));
            }
        }
        return customerDTOS;
    }
}

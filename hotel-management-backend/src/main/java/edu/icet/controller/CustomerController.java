package edu.icet.controller;

import edu.icet.dto.CustomerDTO;
import edu.icet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequiredArgsConstructor
@CrossOrigin(origins ="http://localhost:4200" )
@RequestMapping("/customer")
public class CustomerController {

   private final CustomerService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerDTO> register(@RequestBody CustomerDTO customerDTO){
        System.out.println(customerDTO);
        return service.saveCustomer(customerDTO);
    }
    @PostMapping
    @RequestMapping("/edit")
    public ResponseEntity<CustomerDTO> edit(@RequestBody CustomerDTO customerDTO){
        return service.editCustomer(customerDTO);
    }
    @GetMapping
    @RequestMapping("/find-by-id/{id}")
    public ResponseEntity<CustomerDTO> findById(@RequestParam Long id){
        return service.findById(id);
    }
    @GetMapping
    @RequestMapping("/find-by-username/{username}")
    public ResponseEntity<CustomerDTO> findByUsername(@RequestParam String username){
        return service.findByUsername(username);
    }
    @GetMapping
    @RequestMapping("/all")
    public List<CustomerDTO> getCustomers(){
        return service.getAll();
    }
    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public ResponseEntity<String> delete(@RequestParam Long id){
        return service.deleteCustomerById(id);
    }
}

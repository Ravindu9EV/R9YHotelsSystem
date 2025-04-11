package edu.icet.repo;

import edu.icet.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Optional<Customer> findById(Long id);
    Optional<Customer> findByUsername(String username);


}

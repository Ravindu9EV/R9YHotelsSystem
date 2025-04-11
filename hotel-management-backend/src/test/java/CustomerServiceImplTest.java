import edu.icet.Main;
import edu.icet.dto.CustomerDTO;
import edu.icet.repo.CustomerRepo;
import edu.icet.service.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
public class CustomerServiceImplTest {
   @Autowired
    private CustomerServiceImpl service;
    @Autowired
    private  CustomerRepo repo;
    @Autowired
    private ModelMapper mapper;


    @Test
    void shouldReturnCustomerDTO_whenSavingValidCustomer(){
        CustomerDTO cus=new CustomerDTO(null,"userp2k","user123","userj1@gmail.com","9412345667","Matara",26);
        ResponseEntity<CustomerDTO> response=service.saveCustomer(cus);
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void shouldReturnError_whenCustomerDtoIsNull(){
        ResponseEntity<CustomerDTO> response=service.saveCustomer(null);
        assertEquals(500,response.getStatusCodeValue());
    }

    @Test
    void shouldReturnError_whenUsernameIsNull(){
        CustomerDTO cus=new CustomerDTO(null,"","user123","usenr4@gmail.com","9412345667","tara",26);
        ResponseEntity<CustomerDTO> response=service.saveCustomer(cus);
        assertEquals(400,response.getStatusCodeValue());

    }
    @Test
    void shouldReturnError_whenEmailIsNull(){
        CustomerDTO cus=new CustomerDTO(null,"userklop5o","user12op3","","9412345667","tara",26);
        ResponseEntity<CustomerDTO> response=service.saveCustomer(cus);
        assertEquals(400,response.getStatusCodeValue());

    }
    @Test
    void shouldReturnError_whenPasswordIsNull(){
        CustomerDTO cus=new CustomerDTO(null,"useropp6i","","user6pp@gmail.com","9412345667","tara",26);
        ResponseEntity<CustomerDTO> response=service.saveCustomer(cus);
        assertEquals(400,response.getStatusCodeValue());

    }
    @Test
    void shouldReturnCustomerDto_whenIdIsNotNull(){
        ResponseEntity<CustomerDTO> response=service.findById(2L);
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void shouldReturnError_whenIdIsNull(){
        ResponseEntity<CustomerDTO> response=service.findById(null);
        assertEquals(400,response.getStatusCodeValue());
    }

    @Test
    void shouldReturnError_whenIdIsLessThanZero(){
        ResponseEntity<CustomerDTO> response=service.findById(-1L);
        assertEquals(400,response.getStatusCodeValue());
    }
    @Test
    void shouldReturnError_whenIdIsZero(){
        ResponseEntity<CustomerDTO> response=service.findById(0L);
        assertEquals(400,response.getStatusCodeValue());
    }

    @Test
    void shouldDeleteCustomerSuccessfully_whenIdIsValid(){
        ResponseEntity<String> response=service.deleteCustomerById(2L);
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void shouldReturnCustomerDto_whenUsernameIsValid(){
        ResponseEntity<CustomerDTO> response=service.findByUsername("user2k");
        assertEquals(200, response.getStatusCodeValue());
    }
    @Test
    void shouldReturnErrorDeletingCustomer_whenIdIsInValid(){
        ResponseEntity<String> response=service.deleteCustomerById(0L);
        assertEquals(400,response.getStatusCodeValue());
    }

    @Test
    void shouldDeleteCustomerSuccessfully_whenUsernameIsValid(){
        ResponseEntity<String> response=service.deleteCustomerByUserName("user2k");
        assertEquals(200,response.getStatusCodeValue());
    }




}

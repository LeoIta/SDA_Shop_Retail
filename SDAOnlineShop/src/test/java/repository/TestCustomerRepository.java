package repository;

import model.Customer;
import org.junit.jupiter.api.*;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class TestCustomerRepository {

    public Customer customer1, customer2, customer3;

    @BeforeEach
    public void start() {
        customer1 = new Customer("Leo","Lorusso","leo@gmail.com","+481234567",1,1);
        customer2 = new Customer("Mikael","Gael","Mikael@gmail.com","+372123456",2,2);
        customer3 = new Customer("Joel","Raqid","Joel@gmail.com","+391234567",2,2);
    }

    @Test
    @Order(1)
    @DisplayName("FindAll")
    public void checkFindAll(){
        List<Customer> customerList = CustomerRepository.findAll();

        Assertions.assertEquals(customer1.toString(),customerList.get(0).toString());
        Assertions.assertEquals(customer2.toString(),customerList.get(1).toString());
    }

    @Test()
    @Order(2)
    @DisplayName("getLastAddedCustomer")
    public void checkGetLastAddedAddress(){
        Customer customer = CustomerRepository.getLastAddedCustomer();
        Assertions.assertEquals(customer2.toString(),customer.toString());
    }

    @Test
    @Order(3)
    @DisplayName("FindById")
    public void checkFindById(){
        Customer customer = CustomerRepository.findById(1);
        Assertions.assertEquals(customer1.toString(),customer.toString());
    }

    @Test
    @Order(4)
    @DisplayName("SaveNew")
    public void checkSaveNewCustomer(){
        List<Customer> customerList = CustomerRepository.findAll();
        customerList.size();
        CustomerRepository.saveNewCustomer(customer3);
        List<Customer> customerList1 = CustomerRepository.findAll();
        assert customerList1.size() - customerList.size() == 1;
        Assertions.assertEquals(customer3.toString(),customerList1.get(2).toString());
    }

    @Test
    @Order(5)
    @DisplayName("UpdateCustomerById")
    public void checkUpdateCustomerById(){
        CustomerRepository.updateCustomerById(3,customer2);
        Customer customer = CustomerRepository.findById(3);
        Assertions.assertEquals(customer2.toString(),customer.toString());
    }

    @Test()
    @Order(6)
    @DisplayName("GetLastCustomerId")
    public void checkGetLastCustomerId(){
            assert CustomerRepository.getLastCustomerId()==3;
        }

    @Test
    @Order(7)
    @DisplayName("DeleteCustomerById")
    public void checkDeleteCustomerById(){
        int size = CustomerRepository.findAll().size();
        int lastId = CustomerRepository.getLastCustomerId();
        CustomerRepository.deleteCustomerById(lastId);
        List<Customer> customerList = CustomerRepository.findAll();
        assert size - customerList.size() == 1;
        Assertions.assertEquals(customer2.toString(),customerList.get(customerList.size()-1).toString());
    }

}

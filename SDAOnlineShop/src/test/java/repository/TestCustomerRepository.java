package repository;
import model.Customer;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

    public class TestCustomerRepository {
    public List<Customer> customerListTest = new ArrayList<Customer>();
    public Customer customer1;
    public Customer customer2;
    public Customer customer3;

    @BeforeEach
    public void start() {
        customer1 = new Customer("Leo","Lorusso","leo@gmail.com","+481234567",1,1);
        customer2 = new Customer("Mikael","Gael","Mikael@gmail.com","+372123456",2,2);
        customerListTest.add(customer1);
        customerListTest.add(customer2);
        customer3 = new Customer("Joel","Raqid","Joel@gmail.com","+391234567",2,2);
        CustomerRepository.updateCustomerById(2,customer2);
    }

    @Test
    @DisplayName("FindAll")
    public void checkFindAll(){
        List<Customer> customerList = CustomerRepository.findAll();

        Assertions.assertEquals(customerListTest.get(0).toString(),customerList.get(0).toString());
        Assertions.assertEquals(customerListTest.get(1).toString(),customerList.get(1).toString());
    }

    @Test()
    @DisplayName("getLastAddedCustomer")
    public void checkGetLastAddedAddress(){
        Customer customer = CustomerRepository.getLastAddedCustomer();
        Assertions.assertEquals(customer3.toString(),customer.toString());
    }
    @Test
    @DisplayName("FindById")
    public void checkFindById(){
        List<Customer> customerList = CustomerRepository.findById(2);
        Assertions.assertEquals(customer2.toString(),customerList.get(0).toString());
    }

    @Test
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
    @DisplayName("UpdateCustomerById")
    public void checkUpdateCustomerById(){
        CustomerRepository.updateCustomerById(2,customer3);
        List<Customer> customerList = CustomerRepository.findById(2);
        Assertions.assertEquals(customer3.toString(),customerList.get(0).toString());
    }

        @Test()
        @DisplayName("GetLastCustomerId")
        public void checkGetLastCustomerId(){
            assert CustomerRepository.getLastCustomerId()==3;
        }

    @Test
    @DisplayName("DeleteCustomerById")
    public void checkDeleteCustomerById(){
        List<Customer> customerList = CustomerRepository.findAll();
        int size = customerList.size();
        int lastId = CustomerRepository.getLastCustomerId();
        CustomerRepository.deleteCustomerById(lastId);
        customerList = CustomerRepository.findAll();
        assert size - customerList.size() == 1;
        Assertions.assertEquals(customer2.toString(),customerList.get(customerList.size()-1).toString());
    }

}

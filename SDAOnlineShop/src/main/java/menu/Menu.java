package menu;
import model.Customer;
import repository.*;

import java.util.List;

public class Menu {
    public static void main(String[] args) {

        List<Customer> customers = CustomerRepository.findAll();

        for ( Customer cust : customers){
            System.out.println(cust.toString());
        }



    }
}

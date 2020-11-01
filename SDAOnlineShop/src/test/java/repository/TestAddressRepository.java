package repository;

import model.Address;
import org.junit.jupiter.api.BeforeEach;

public class TestAddressRepository {


    public Address address;

    @BeforeEach
    public void start() { address = new Address(1,"Italy","Rome","00186","Via del Corso 114/115");}
}

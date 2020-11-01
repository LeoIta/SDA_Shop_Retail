package repository;

import model.Address;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class TestAddressRepository {



    public List<Address> address = new ArrayList<Address>();

    @BeforeEach
    public void start() {
        Address address1 = new Address(1,"Estonia","Tallin","10145","Maakri 19/1");
        Address address2 = new Address(2,"Poland","Gdynia","81-451","aleja Zwyciestwa 96/98");

        address.add(address1);
        address.add(address2);

        Address address3 = new Address(3,"Italy","Rome","00186","Via del Corso 114/115");
        }

    @Test
    @DisplayName("FindAll")
    public void checkFindAll(){
        List<Address> address2 = AddressRepository.findAll();
        assert address.get(0).getAddressID()== address2.get(0).getAddressID();
        assert address.get(0).getCountry().equals(address2.get(0).getCountry());
        assert address.get(0).getCity().equals(address2.get(0).getCity());
        assert address.get(0).getPostalCode().equals(address2.get(0).getPostalCode());
        assert address.get(0).getStreet().equals(address2.get(0).getStreet());

        assert address.get(1).getAddressID()== address2.get(1).getAddressID();
        assert address.get(1).getCountry().equals(address2.get(1).getCountry());
        assert address.get(1).getCity().equals(address2.get(1).getCity());
        assert address.get(1).getPostalCode().equals(address2.get(1).getPostalCode());
        assert address.get(1).getStreet().equals(address2.get(1).getStreet());
        }
}
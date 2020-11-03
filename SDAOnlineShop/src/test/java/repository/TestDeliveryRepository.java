package repository;
import model.Delivery;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

public class TestDeliveryRepository {

        public List<Delivery> deliveryListTest = new ArrayList<Delivery>();
        public Delivery delivery1;
        public Delivery delivery2;
        public Delivery delivery3;

        @BeforeEach
        public void start() {
            delivery1 = new Delivery("DHL",20);
            delivery2 = new Delivery("FEDEX",30);
            deliveryListTest.add(delivery1);
            deliveryListTest.add(delivery2);
            delivery3 = new Delivery("UPS",25);
        }

        @Test
        @DisplayName("FindAll")
        public void checkFindAll(){
            List<Delivery> deliveryList = DeliveryRepository.findAll();

            Assertions.assertEquals(deliveryListTest.get(0).toString(),deliveryList.get(0).toString());
            Assertions.assertEquals(deliveryListTest.get(1).toString(),deliveryList.get(1).toString());
        }

        @Test
        @DisplayName("FindById")
        public void checkFindById(){
            Delivery deliveryList = DeliveryRepository.findById(2);
            Assertions.assertEquals(delivery2.toString(),deliveryList.toString());
        }

        @Test
        @DisplayName("SaveNew")
        public void checkSaveNewDelivery(){
            List<Delivery> deliveryList = DeliveryRepository.findAll();
            deliveryList.size();
            DeliveryRepository.saveNewDelivery(delivery3);
            List<Delivery> deliveryList1 = DeliveryRepository.findAll();
            assert deliveryList1.size() - deliveryList.size() == 1;
            Assertions.assertEquals(delivery3.toString(),deliveryList1.get(2).toString());
        }

        @Test
        @DisplayName("UpdateDeliveryById")
        public void checkUpdateDeliveryById(){
            DeliveryRepository.updateDeliveryById(2,delivery3);
            Delivery deliveryList = DeliveryRepository.findById(2);
            Assertions.assertEquals(delivery3.toString(),deliveryList.toString());
        }

        @Test()
        @DisplayName("GetLastDeliveryId")
        public void checkGetLastDeliveryId(){
            assert DeliveryRepository.getLastDeliveryId()==3;
        }

        @Test
        @DisplayName("DeleteDeliveryById")
        public void checkDeleteDeliveryById(){
            List<Delivery> deliveryList = DeliveryRepository.findAll();
            int size = deliveryList.size();
            int lastId = DeliveryRepository.getLastDeliveryId();
            DeliveryRepository.deleteDeliveryById(lastId);
            deliveryList = DeliveryRepository.findAll();
            assert size - deliveryList.size() == 1;
            Assertions.assertEquals(delivery3.toString(),deliveryList.get(deliveryList.size()-1).toString());
        }

}

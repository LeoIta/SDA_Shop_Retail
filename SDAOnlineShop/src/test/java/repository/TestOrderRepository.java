package repository;
import model.Order;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

    public class TestOrderRepository {

        public List<Order> orderListTest = new ArrayList<Order>();
        public Order order1, order2 , order3, order4, order5;

        @BeforeEach
        public void start() {
            order1 = new Order(1,1,1,1);
            order2 = new Order(2,2,2,2);
            orderListTest.add(order1);
            orderListTest.add(order2);
            order3 = new Order(3,2,3,2);
            order4 = new Order(2,2,2,1);
            order5 = new Order(2,2,1,1);
        }

        @Test
        @DisplayName("FindAll")
        public void checkFindAll(){
            List<Order> orderList = OrderRepository.findAll();

            Assertions.assertEquals(order1.toString(),orderList.get(0).toString());
            Assertions.assertEquals(order4.toString(),orderList.get(1).toString());
        }

        @Test
        @DisplayName("FindById")
        public void checkFindById(){
            List<Order> orderList = OrderRepository.findById(1);
            Assertions.assertEquals(order1.toString(),orderList.get(0).toString());
        }

        @Test
        @DisplayName("SaveNew")
        public void checkSaveNewOrder(){
            List<Order> orderList = OrderRepository.findAll();
            orderList.size();
            OrderRepository.saveNewOrder(order3);
            List<Order> orderList1 = OrderRepository.findAll();
            assert orderList1.size() - orderList.size() == 1;
            Assertions.assertEquals(order3.toString(),orderList1.get(2).toString());
        }

        @Test
        @DisplayName("UpdateDeliveryById")
        public void checkUpdateDeliveryById(){
            OrderRepository.updateDeliveryById(1,order2);
            List<Order> orderList = OrderRepository.findAll();
            Assertions.assertEquals(order5.toString(),orderList.get(1).toString());
        }
        @Test
        @DisplayName("UpdateProductById")
        public void checkUpdateProductById(){
            OrderRepository.updateProductById(1,order2);
            List<Order> orderList = OrderRepository.findAll();
            Assertions.assertEquals(order4.toString(),orderList.get(1).toString());
        }

        @Test()
        @DisplayName("GetLastOrderId")
        public void checkGetLastOrderId(){
            assert OrderRepository.getLastOrderId()==3;
        }

        @Test
        @DisplayName("DeleteOrderById")
        public void checkDeleteOrderById(){
            List<Order> orderList = OrderRepository.findAll();
            int size = orderList.size();
            int lastId = OrderRepository.getLastOrderId();
            OrderRepository.deleteOrderById(lastId);
            orderList = OrderRepository.findAll();
            assert size - orderList.size() == 1;
            Assertions.assertEquals(order4.toString(),orderList.get(orderList.size()-1).toString());
        }

    }


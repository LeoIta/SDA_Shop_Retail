package repository;
import model.Product;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

    public class TestProductRepository {

        public List<Product> productListTest = new ArrayList<Product>();
        public Product product1, product2, product3, product4,product5,product13,product14,product15,product16,product17;

        @BeforeEach
        public void start() {
            product1 = new Product("JEANS","RED","XS","0JNS00XS00RD",40);
            product2 = new Product("SHIRT","BLUE","XL","0SHT00XL00BE",25);
            productListTest.add(product1);
            productListTest.add(product2);
            product3 = new Product("T-SHIRT","GREEN","XS","0TST00XS00GN",10);
            product4 = new Product("SHIRT","GREEN","XL","0TST00XL00GN",10);
            product5 = new Product("T-SHIRT","GREEN","XS","0TST00XS00GN",10);

            product13=new Product("T-SHIRT","GREEN","XS","0TST00XS00GN",15);
            product14=new Product("SKIRT","BLACK","XS","0SKT00XS00BK",40);
            product15=new Product("TROUSERS","YELLOW","XS","0TRS00XS00YW",45);
            product16=new Product("JACKET","WHITE","S","0JKT00SS00WE",60);
            product17=new Product("COAT","RED","XL","0CAT00XL00RD",100);
        }

        @Test()
        @DisplayName("FindAll")
        public void checkFindAll(){
            List<Product> productList = ProductRepository.findAll();

            Assertions.assertEquals(product1.toString(),productList.get(0).toString());
            Assertions.assertEquals(product4.toString(),productList.get(1).toString());
            Assertions.assertEquals(product13.toString(),productList.get(2).toString());
            Assertions.assertEquals(product14.toString(),productList.get(3).toString());
            Assertions.assertEquals(product15.toString(),productList.get(4).toString());
            Assertions.assertEquals(product16.toString(),productList.get(5).toString());
            Assertions.assertEquals(product17.toString(),productList.get(6).toString());

        }

        @Test()
        @DisplayName("SaveNew")
        public void checkSaveNewProduct(){
            List<Product> productList = ProductRepository.findAll();
            productList.size();
            ProductRepository.saveNewProduct(product2);
            List<Product> productList1 = ProductRepository.findAll();
            assert productList1.size() - productList.size() == 1;
            Assertions.assertEquals(product2.toString(),productList1.get(6).toString());
        }

        @Test()
        @DisplayName("GetLastProductId")
        public void checkGetLastProductId(){
            assert ProductRepository.getLastProductId()==7;
        }

        @Test()
        @DisplayName("FindById")
        public void checkFindById(){
            List<Product> product = ProductRepository.findById(1);
            Assertions.assertEquals(product1.toString(),product.get(0).toString());
        }

        @Test()
        @DisplayName("UpdateProductById")
        public void checkUpdateProductById(){
            ProductRepository.updateProductById(2,product4);
            List<Product> product = ProductRepository.findById(2);
            Assertions.assertEquals(product4.toString(),product.get(0).toString());
        }

        @Test()
        @DisplayName("DeleteProductById")
        public void checkDeleteProductById(){
            List<Product> productList = ProductRepository.findAll();
            int size = productList.size();
            int lastId = ProductRepository.getLastProductId();
            ProductRepository.deleteProductById(lastId);
            productList = ProductRepository.findAll();
            assert size - productList.size() == 1;
            Assertions.assertEquals(product16.toString(),productList.get(productList.size()-1).toString());
        }


}

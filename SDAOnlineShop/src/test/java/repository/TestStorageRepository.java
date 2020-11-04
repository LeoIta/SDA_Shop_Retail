package repository;
import model.Address;
import model.Storage;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;
public class TestStorageRepository {
    public List<Storage> storageListTest = new ArrayList<Storage>();
    public Storage storage1;
    public Storage storage2;
    public Storage storage3;

    @BeforeEach
    public void start() {
        storage1 = new Storage("0CAT00LL00BE",1);
        storage2 = new Storage("0CAT00LL00BK",9);
        storageListTest.add(storage1);
        storageListTest.add(storage2);
        storage3 = new Storage("0CAT00LL00GN",1);
    }

    @Test()
    @DisplayName("FindAll")
    public void checkFindAll(){
        List<Storage> storageList = StorageRepository.findAll();

        Assertions.assertEquals(storageListTest.get(0).toString(),storageList.get(0).toString());
        Assertions.assertEquals(storageListTest.get(1).toString(),storageList.get(1).toString());
        (new Storage("0JNS00MM00RD",3)).toString().equals(storageList.get(2).toString());
        (new Storage("0JNS00LL00RD",9)).toString().equals(storageList.get(3).toString());
        (new Storage("0JNS00XL00RD",0)).toString().equals(storageList.get(4).toString());
        (new Storage("0JNS00XS00GN",10)).toString().equals(storageList.get(5).toString());
        (new Storage("0JNS00SS00GN",5)).toString().equals(storageList.get(6).toString());
        (new Storage("0JNS00MM00GN",11)).toString().equals(storageList.get(7).toString());
    }

    @Test()
    @DisplayName("findByCode")
    public void checkFindByCode(){
        Storage storage = StorageRepository.findByCode("0JNS00MM00GN");
        Assertions.assertEquals((new Storage("0JNS00MM00GN",11)).toString(),storage.toString());
    }

    @Test()
    @DisplayName("getQtyByCode")
    public void checkGetQtyByCode(){
        assert StorageRepository.getQtyByCode("0JNS00SS00GN")==5;
    }

    @Test()
    @DisplayName("UpdateStorageByCode")
    public void checkUpdateStorageByCode(){
        StorageRepository.updateStorageByCode("0JNS00XS00GN",2);
        assert StorageRepository.getQtyByCode("0JNS00XS00GN")==8;
    }

    @Test()
    @DisplayName("DeleteStorageById")
    public void checkDeleteStorageById(){
        List<Storage> storageList = StorageRepository.findAll();
        int size = storageList.size();
        StorageRepository.deleteStorageByCode("0CAT00MM00BE");
        storageList = StorageRepository.findAll();
        assert size - storageList.size() == 1;
        Storage storageDeleted = StorageRepository.findByCode("0CAT00MM00BE");
       Assertions.assertTrue(storageDeleted.getProductCode()==null && storageDeleted.getAvailable_quantity()==0);
    }

}

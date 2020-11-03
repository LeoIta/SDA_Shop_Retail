package repository;
import model.Login;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;


public class TestLoginRepository {

        public List<Login> loginListTest = new ArrayList<Login>();
        public Login login1;
        public Login login2;
        public Login login3;

        @BeforeEach
        public void start() {
            login1 = new Login("Leo","Leo");
            login2 = new Login("Mikael","Mikael");
            loginListTest.add(login1);
            loginListTest.add(login2);
            login3 = new Login("Joel","Joel");
        }

        @Test
        @DisplayName("FindAll")
        public void checkFindAll(){
            List<Login> loginList = LoginRepository.findAll();

            Assertions.assertEquals(loginListTest.get(0).toString(),loginList.get(0).toString());
            Assertions.assertEquals(loginListTest.get(1).toString(),loginList.get(1).toString());
        }

        @Test()
        @DisplayName("getLastAddedLogin")
        public void checkGetLastAddedLogin(){
            Login login = LoginRepository.getLastAddedLogin();
            Assertions.assertEquals(login2.toString(),login.toString());
        }

        @Test
        @DisplayName("FindById")
        public void checkFindById(){
            List<Login> loginList = LoginRepository.findById(1);
            Assertions.assertEquals(login1.toString(),loginList.get(0).toString());
        }

        @Test
        @DisplayName("SaveNew")
        public void checkSaveNewLogin(){
            List<Login> loginList = LoginRepository.findAll();
            loginList.size();
            LoginRepository.saveNewLogin(login3);
            List<Login> loginList1 = LoginRepository.findAll();
            assert loginList1.size() - loginList.size() == 1;
            Assertions.assertEquals(login3.toString(),loginList1.get(2).toString());
        }

        @Test
        @DisplayName("UpdateLoginById")
        public void checkUpdateLoginById(){
            LoginRepository.updateLoginById(2,login3);
            List<Login> loginList = LoginRepository.findById(2);
            Assertions.assertEquals(login3.toString(),loginList.get(0).toString());
        }

        @Test()
        @DisplayName("GetLastLoginId")
        public void checkGetLastLoginId(){
            assert LoginRepository.getLastLoginId()==2;
        }

        @Test
        @DisplayName("DeleteLoginById")
        public void checkDeleteLoginById(){
            List<Login> loginList = LoginRepository.findAll();
            int size = loginList.size();
            int lastId = LoginRepository.getLastLoginId();
            LoginRepository.deleteLoginById(lastId);
            loginList = LoginRepository.findAll();
            assert size - loginList.size() == 1;
            Assertions.assertEquals(login2.toString(),loginList.get(loginList.size()-1).toString());
        }

        @Test
        @DisplayName("FindAccountId")
        public void checkFindAccountId(){
        Assertions.assertEquals(login1.toString(),LoginRepository.findAccountId("Leo","Leo").get(0).toString());
        }


    }


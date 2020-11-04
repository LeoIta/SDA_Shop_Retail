package repository;

import model.Login;
import org.junit.jupiter.api.*;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestLoginRepository {

        public Login login1, login2, login3, login4;

        @BeforeEach
        public void start() {
            login1 = new Login("Leo","Leo");
            login2 = new Login("Mikael","Mikael");
            login3 = new Login("Joel","Joel");
            login4 = new Login("guest","guest");
        }

        @Test
        @Order(1)
        @DisplayName("FindAll")
        public void checkFindAll(){
            List<Login> loginList = LoginRepository.findAll();

            Assertions.assertEquals(login1.toString(),loginList.get(0).toString());
            Assertions.assertEquals(login2.toString(),loginList.get(1).toString());
        }

        @Test()
        @Order(2)
        @DisplayName("getLastAddedLogin")
        public void checkGetLastAddedLogin(){
            Login login = LoginRepository.getLastAddedLogin();
            Assertions.assertEquals(login4.toString(),login.toString());
        }

        @Test
        @Order(3)
        @DisplayName("FindById")
        public void checkFindById(){
            Login login = LoginRepository.findById(1);
            Assertions.assertEquals(login1.toString(),login.toString());
        }

        @Test
        @Order(4)
        @DisplayName("SaveNew")
        public void checkSaveNewLogin(){
            int size = LoginRepository.findAll().size();
            LoginRepository.saveNewLogin(login3);
            List<Login> loginList = LoginRepository.findAll();
            assert loginList.size() - size == 1;
            Assertions.assertEquals(login3.toString(),loginList.get(3).toString());
        }

        @Test()
        @Order(5)
        @DisplayName("GetLastLoginId")
        public void checkGetLastLoginId(){
            assert LoginRepository.getLastLoginId()==4;
        }

        @Test
        @Order(6)
        @DisplayName("DeleteLoginById")
        public void checkDeleteLoginById(){
            int size = LoginRepository.findAll().size();
            int lastId = LoginRepository.getLastLoginId();
            LoginRepository.deleteLoginById(lastId);
            List<Login> loginList = LoginRepository.findAll();
            assert size - loginList.size() == 1;
            Assertions.assertEquals(login4.toString(),loginList.get(loginList.size()-1).toString());
        }

        @Test
        @Order(7)
        @DisplayName("UpdateLoginById")
        public void checkUpdateLoginById(){
            LoginRepository.updateLoginById(2,login3);
            Login login = LoginRepository.findById(2);
            Assertions.assertEquals(login3.toString(),login.toString());
        }

        @Test
        @Order(8)
        @DisplayName("FindAccountId")
        public void checkFindAccountId(){
            Assertions.assertEquals(login1.toString(),LoginRepository.findAccountId("Leo","Leo").toString());
        }

        @Test
        @Order(9)
        @DisplayName("Check if account is already in use")
        public void checkAccountInUse(){
            Assertions.assertTrue(LoginRepository.accountInUse("Leo"));
        }


    }


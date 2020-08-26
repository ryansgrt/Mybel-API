//package com.enigma.mybel.services;
//
//import com.enigma.mybel.entity.*;
//import com.enigma.mybel.repositories.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.mock.web.MockMultipartFile;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class UserServiceDbImplTest {
//    @Autowired
//    UserService service;
//
//    @Autowired
//    UserRepository repository;
//
//    @BeforeEach
//    public void cleanUp(){
//        repository.deleteAll();
//    }
//
//    @Test
//    void saveUser_shouldAdd_1Data_inDB_whenUserSaved() throws IOException {
//        String user = "{\"name\":\"zeus\",\"username\":\"enigma\",\"email\":\"zeus@gmail.com\",\"address\":\"enigmacamp\",\"phone\":\"081231139275\",\"password\":\"zeuszeus\"}";
//        MockMultipartFile file = new MockMultipartFile("data", "filename.jpg", "jpg", "jpg".getBytes());
//        service.saveUser(file,user);
//        assertEquals(1,repository.findAll().size());
//    }
//
//    @Test
//    void saveUserForm_shouldAdd_1Data_inDB_whenUserSaved() {
//        User user=new User("zeus","enigma","zeusemail","enigmacamp","098","zeuszeus");
//        service.saveFormUser(user);
//        assertEquals(1,repository.findAll().size());
//    }
//
//    @Test
//    void deleteUser_shouldDelete_1Data_inDB_whenTypeDeleted() {
//        User user=new User("zeus","enigma","zeusemail","enigmacamp","098","zeuszeus");
//        User user1=new User("zeus1","enigma1","zeusemail1","enigmacamp1","0981","zeuszeus1");
//
//        repository.save(user);
//        repository.save(user1);
//        service.deleteUser(user.getId());
//        assertEquals(1,repository.findAll().size());
//    }
//
//    @Test
//    void getAllUser_shouldBe_2InDB_whenDataInDBIs_2() {
//        User user=new User("zeus","enigma","zeusemail","enigmacamp","098","zeuszeus");
//        User user1=new User("zeus1","enigma1","zeusemail1","enigmacamp1","0981","zeuszeus1");
//
//        repository.save(user);
//        repository.save(user1);
//        assertEquals(2, service.getAllUser(Pageable.unpaged()).getTotalElements());
//    }
//
//    @Test
//    void getUserById_shouldGetUser_whenGivenCorrectId() {
//        User user=new User("zeus","enigma","zeusemail","enigmacamp","098","zeuszeus");
//        User user1=new User("zeus1","enigma1","zeusemail1","enigmacamp1","0981","zeuszeus1");
//
//        repository.save(user);
//        repository.save(user1);
//
//        assertEquals(user1, service.getUser(user1.getId()));
//        assertEquals(user, service.getUser(user.getId()));
//    }
//
//    @Test
//    void updateUser_shouldNotChangeAmount_ofUserInDb_whenUpdated() throws IOException {
//        User user=new User("zeus","enigma","zeusemail","enigmacamp","098","zeuszeus");
//        repository.save(user);
//        List<Transaction> transactionList = new ArrayList<>();
//
//
//        transactionList.add(new Transaction());
//
//        user.setPhoto("vendor/id");
//        user.setAddress("jagakarsa");
//        user.setEmail("wakdoyok@gmail.com");
//        user.setName("Wak doyok");
//        user.setPassword("satusatu");
//        user.setPhone("09881232122");
//        user.setStatus(!user.getStatus());
//        user.setUsername("waduwadu");
//        user.setTransactions(transactionList);
//
//        String body =String.format("{\"name\":\"%s\",\"username\":\"%s\",\"email\":\"%s\",\"address\":\"%s\",\"phone\":\"%s\",\"password\":\"%s\",\"id\":\"%s\"}",
//                user.getName(),user.getUsername(),user.getEmail(),user.getAddress(),user.getPhone(),user.getPassword(),user.getId());
//        MockMultipartFile file = new MockMultipartFile("data", "filename.jpg", "photo/plain", "some jpg".getBytes());
//
//        service.updateUser(file,body);
//        assertEquals(1,repository.findAll().size());
//    }
//}

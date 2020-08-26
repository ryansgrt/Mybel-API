//package com.enigma.mybel.services;
//
//import com.enigma.mybel.entity.*;
//import com.enigma.mybel.enums.Gender;
//import com.enigma.mybel.repositories.VendorRepository;
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
//public class VendorServiceDbImplTest {
//    @Autowired
//    VendorService service;
//
//    @Autowired
//    VendorRepository repository;
//
//    @BeforeEach
//    public void cleanUp(){
//        repository.deleteAll();
//    }
//
//    @Test
//    void saveVendor_shouldAdd_1Data_inDB_whenUserSaved() throws IOException {
//        String vendor = "{\"name\":\"zeus\",\"username\":\"enigma\",\"email\":\"zeus@gmail.com\",\"address\":\"enigmacamp\",\"phone\":\"081231139275\",\"gender\":\"MALE\",\"company\":\"enigma\"}";
//        MockMultipartFile file = new MockMultipartFile("data", "filename.jpg", "photo/plain", "some jpg".getBytes());
//        service.saveVendor(file,vendor);
//        assertEquals(1,repository.findAll().size());
//    }
//
//    @Test
//    void saveVendorForm_shouldAdd_1Data_inDB_whenVendorSaved() {
//        Vendor vendor=new Vendor("zeus","enigma","zeuskeren", Gender.MALE,"enigma","zeuszeus@gmail.com","jagakarsa");
//        service.saveFormVendor(vendor);
//        assertEquals(1,repository.findAll().size());
//    }
//
//    @Test
//    void deleteVendor_shouldDelete_1Data_inDB_whenVendorDeleted() {
//        Vendor vendor=new Vendor("zeus","enigma","zeuskeren", Gender.MALE,"enigma","zeuszeus@gmail.com","jagakarsa");
//        Vendor vendor1=new Vendor("zeus1","enigma1","zeuskeren1", Gender.MALE,"enigma1","zeuszeus1@gmail.com","jagakarsa1");
//
//        repository.save(vendor);
//        repository.save(vendor1);
//        service.deleteVendor(vendor.getId());
//        assertEquals(1,repository.findAll().size());
//    }
//
//    @Test
//    void getAllVendor_shouldBe_2InDB_whenDataInDBIs_2() {
//        Vendor vendor=new Vendor("zeus","enigma","zeuskeren", Gender.MALE,"enigma","zeuszeus@gmail.com","jagakarsa");
//        Vendor vendor1=new Vendor("zeus1","enigma1","zeuskeren1", Gender.MALE,"enigma1","zeuszeus1@gmail.com","jagakarsa1");
//
//        repository.save(vendor);
//        repository.save(vendor1);
//        assertEquals(2, service.getAllVendor(Pageable.unpaged()).getTotalElements());
//    }
//
//    @Test
//    void getUserById_shouldGetUser_whenGivenCorrectId() {
//        Vendor vendor=new Vendor("zeus","enigma","zeuskeren", Gender.MALE,"enigma","zeuszeus@gmail.com","jagakarsa");
//        Vendor vendor1=new Vendor("zeus1","enigma1","zeuskeren1", Gender.MALE,"enigma1","zeuszeus1@gmail.com","jagakarsa1");
//
//        repository.save(vendor);
//        repository.save(vendor1);
//
//        assertEquals(vendor, service.getVendor(vendor.getId()));
//        assertEquals(vendor1, service.getVendor(vendor1.getId()));
//    }
//
//    @Test
//    void updateUser_shouldChangeUserInDb_whenUpdated() throws IOException {
//        Vendor vendor=new Vendor("zeus","enigma","zeuskeren", Gender.MALE,"enigma","zeuszeus@gmail.com","jagakarsa");
//        repository.save(vendor);
//
//        List<Unit> unitList = new ArrayList<>();
//        List<DesignInterior> designInteriorList = new ArrayList<>();
//
//        unitList.add(new Unit());
//        designInteriorList.add(new DesignInterior());
//
//        vendor.setPhoto("vendor/id");
//        vendor.setAddress("jagakarsa");
//        vendor.setGender(Gender.FEMALE);
//        vendor.setCompany("enigmacamp");
//        vendor.setEmail("wakdoyok@gmail.com");
//        vendor.setName("Wak doyok");
//        vendor.setPassword("satusatu");
//        vendor.setStatusRequest(!vendor.getStatusRequest());
//        vendor.setUsername("waduwadu");
//        vendor.setDesignInteriors(designInteriorList);
//        vendor.setUnits(unitList);
//
//        vendor.getDesignInteriors();
//        vendor.getUnits();
//
//        String body =String.format("{\"name\":\"%s\",\"username\":\"%s\",\"email\":\"%s\",\"address\":\"%s\",\"gender\":\"%s\",\"password\":\"%s\",\"company\":\"%s\",\"id\":\"%s\"}",
//                vendor.getName(),vendor.getUsername(),vendor.getEmail(),vendor.getAddress(),vendor.getGender(),vendor.getPassword(),vendor.getCompany(),vendor.getId());
//        MockMultipartFile file = new MockMultipartFile("data", "filename.jpg", "photo/plain", "some jpg".getBytes());
//
//        service.updateVendor(file,body);
//        assertEquals(1,repository.findAll().size());
//    }
//
//    @Test
//    void signInByUsername_ShouldGetVendor_whenGivenCorrectUserName_andPassword(){
//        Vendor vendor=new Vendor("zeus","enigma","zeuskeren", Gender.MALE,"enigma","zeuszeus@gmail.com","jagakarsa");
//        repository.save(vendor);
//        Vendor account = service.signInVendor(vendor.getUsername(),vendor.getPassword());
//        assertEquals(vendor.getId(),account.getId());
//    }
//
//    @Test
//    void signInByEmail_ShouldGetVendor_whenGivenCorrectEmail_andPassword(){
//        Vendor vendor=new Vendor("zeus","enigma","zeuskeren", Gender.MALE,"enigma","zeuszeus@gmail.com","jagakarsa");
//        repository.save(vendor);
//        Vendor signIn = service.signInVendor("zeuszeus@gmail.com","zeuskeren");
//        assertEquals(vendor.getId(),signIn.getId());
//    }
//}

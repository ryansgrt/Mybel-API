//package com.enigma.mybel.services;
//
//import com.enigma.mybel.entity.CategoryRoom;
//import com.enigma.mybel.entity.Type;
//import com.enigma.mybel.entity.Unit;
//import com.enigma.mybel.repositories.TypeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class TypeServiceDbImplTest {
//    @Autowired
//    TypeService service;
//
//    @Autowired
//    TypeRepository repository;
//
//    @BeforeEach
//    public void cleanUp(){
//        repository.deleteAll();
//    }
//
//    @Test
//    void saveType_shouldAdd_1Data_inDB_whenTypeSaved() {
//        Type type=new Type("Chair");
//        service.saveType(type);
//        assertEquals(1,repository.findAll().size());
//    }
//
//    @Test
//    void deleteType_shouldDelete_1Data_inDB_whenTypeDeleted() {
//        Type type=new Type("Chair");
//        Type type1=new Type("Table");
//        repository.save(type);
//        repository.save(type1);
//        service.deleteType(type1.getId());
//        assertEquals(1,repository.findAll().size());
//    }
//
//    @Test
//    void getAllType_shouldBe_2InDB_whenDataInDBIs_2() {
//        Type type=new Type("Chair");
//        Type type1=new Type("Table");
//        repository.save(type);
//        repository.save(type1);
//        assertEquals(2, service.getAllType().size());
//    }
//
//    @Test
//    void getTypeById_shouldGetType_whenGivenCorrectId() {
//        Type type=new Type();
//        Type type1=new Type();
//        type.setName("Chair");
//        type1.setName("Table");
//        repository.save(type);
//        repository.save(type1);
//        assertEquals(type1, service.getType(type1.getId()));
//        assertEquals(type, service.getType(type.getId()));
//    }
//
//    @Test
//    void getTypeByName_shouldGetType_whenGivenCorrectName() {
//        Type type=new Type();
//        Type type1=new Type();
//        type.setName("Chair");
//        type1.setName("Table");
//        repository.save(type);
//        repository.save(type1);
//        assertEquals(type1, service.getTypeByName(type1.getName()));
//        assertEquals(type, service.getTypeByName(type.getName()));
//    }
//
//    @Test
//    void updateType_shouldChangeTypeInDb_whenUpdated() {
//        Type type=new Type("Chair");
//        repository.save(type);
//
//        List<Unit> unitList = new ArrayList<>();
//        CategoryRoom room = new CategoryRoom();
//
//
//        type.setUnits(unitList);
//        type.setName("Table");
//        service.updateType(type);
//        type.getRoom();
//        type.getUnits();
//        type.setRoom(room);
//        assertEquals("Table",repository.findById(type.getId()).get().getName());
//    }
//}

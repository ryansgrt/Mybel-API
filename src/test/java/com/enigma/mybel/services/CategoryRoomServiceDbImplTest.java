//package com.enigma.mybel.services;
//
//import com.enigma.mybel.entity.CategoryRoom;
//import com.enigma.mybel.entity.DesignInterior;
//import com.enigma.mybel.entity.Type;
//import com.enigma.mybel.repositories.RoomRepository;
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
//public class CategoryRoomServiceDbImplTest {
//    @Autowired
//    CategoryRoomService service;
//
//    @Autowired
//    RoomRepository repository;
//
//    @BeforeEach
//    public void cleanUp(){
//        repository.deleteAll();
//    }
//
//    @Test
//    void saveCategory_shouldAdd_1Data_inDB_whenCategorySaved() {
//        CategoryRoom room=new CategoryRoom("Living Room");
//        service.saveCategory(room);
//        assertEquals(1,repository.findAll().size());
//    }
//
//    @Test
//    void deleteCategoryRoom_shouldDelete_1Data_inDB_whenCategoryRoomDeleted() {
//        CategoryRoom room=new CategoryRoom("Living Room");
//        CategoryRoom room1=new CategoryRoom("Dining Room");
//        repository.save(room);
//        repository.save(room1);
//        service.deleteCategory(room1.getId());
//        assertEquals(1,repository.findAll().size());
//    }
//
//    @Test
//    void getAllCategoryRoom_shouldBe_2InDB_whenDataInDBIs_2() {
//        CategoryRoom room=new CategoryRoom("Living Room");
//        CategoryRoom room1=new CategoryRoom("Dining Room");
//        repository.save(room);
//        repository.save(room1);
//        assertEquals(2, service.getAllCategory().size());
//    }
//
//    @Test
//    void getCategoryRoomByName_shouldGetCategoryRoom_whenGivenCorrectName() {
//        CategoryRoom room=new CategoryRoom();
//        CategoryRoom room1=new CategoryRoom();
//        room.setName("Living Room");
//        room1.setName("Dining Room");
//        repository.save(room);
//        repository.save(room1);
//        assertEquals(room1, service.getCategoryByName(room1.getName()));
//        assertEquals(room, service.getCategoryByName(room.getName()));
//    }
//
//    @Test
//    void updateCategoryRoom_shouldChangeCategoryNameInDb_whenUpdated() {
//        CategoryRoom room=new CategoryRoom("Living Room");
//        service.saveCategory(room);
//
//        CategoryRoom myRoom = repository.findByNameEquals(room.getName());
//
//        List<Type> roomTypes = new ArrayList<>();
//        List<DesignInterior> designInteriorList = new ArrayList<>();
//
//        roomTypes.add(new Type("Chair"));
//        designInteriorList.add(new DesignInterior("Dark Comet", 22240000.0, "Beautiful comet theme",14));
//
//        room.setId(myRoom.getId());
//        room.setTypes(roomTypes);
//        room.setDesignInteriors(designInteriorList);
//        room.setName("Dining Room");
//        service.updateCategory(room);
//        assertEquals("Dining Room",repository.findById(room.getId()).get().getName());
//    }
//}

//package cvapp.repository;
//
//
//import cvapp.model.CV;
//import cvapp.model.Contact;
//import org.junit.jupiter.api.*;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class JsonDBTest {
//
//    JsonDB jsonDB;
//    @BeforeEach
//    void beforeEach() {
//        jsonDB = new JsonDB("testdb.json");
//    }
//    @AfterEach
//    void after() throws IOException {
//        Files.deleteIfExists(Path.of("testdb.json"));
//    }
//
//    @Test
//    void add_Then_Files_contains_data() throws IOException {
//        CV cv = new CV();
//        cv.setFirstName("Test");
//        jsonDB.add(cv);
//
//        String fileContent = Files.readString(Path.of("testdb.json"));
//
//        assertTrue(fileContent.contains("Test"));
//    }
//
//    @Test
//    @DisplayName("when i add a cv i found it in the cvs list")
//    void addT1() {
//        CV cv = new CV();
//        cv.setFirstName("Test");
//        jsonDB.add(cv);
//
//        boolean isCVPresent = jsonDB.findCVByFirstName("Test").isPresent();
//
//        assertTrue(isCVPresent);
//
//    }
//
//    @Test
//    void name() {
//
//        assertFalse(Contact.isValidEmailAddress("asdas"));
//    }
//
//    @Test
//    void loadAllCVs() {
//    }
//
//    @Test
//    void findCVByFirstName() {
//    }
//
//    @Test
//    void delete() {
//    }
//}
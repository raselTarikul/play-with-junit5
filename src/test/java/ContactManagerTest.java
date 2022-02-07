import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactManagerTest {

    ContactManager contactManager;

    @BeforeAll
    public void setUpALL(){
        System.out.println("Db setup will go here");
    }

    @BeforeEach
    public void setUp(){
        contactManager = new ContactManager();
    }


    @Test
    public void shouldCreateContact(){
        contactManager.addContact("Tarikul", "Islam", "0987654680");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should Fail with null firstname")
    public void shouldNotCreateContactWhenFirstNameNull(){

        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Islam", "0987654680");
        });
    }

    @Test
    @DisplayName("Should Fail with null lastname")
    public void shouldNotCreateContactWhenLastNameNull(){

        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Tarikul", null, "0987654680");
        });
    }

    @Test
    @DisplayName("Should Fail with null phone no")
    public void shouldNotCreateContactWhenPhoneNumberNull(){

        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Tarikul", "Islam", null);
        });
    }

    @AfterEach
    public void cleanUp(){
        System.out.println("Clean up");
    }

    @AfterAll
    public void cleanAll(){
        System.out.println("Clean all");
    }


}

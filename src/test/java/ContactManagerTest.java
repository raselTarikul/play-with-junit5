import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactManagerTest {

    ContactManager contactManager;


    @BeforeEach
    public void setUp(){
        contactManager = new ContactManager();
    }

    @DisplayName("parameterize Test")
    @ParameterizedTest
    @MethodSource("getPhoneNo")
    public void parameterizeTestMethodSource(String phoneNo){
        contactManager.addContact("Tarikul", "Islam", phoneNo);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    private List<String> getPhoneNo(){
        return Arrays.asList("0987654680", "0987654690", "0987654670");
    }

    @AfterEach
    public void cleanUp(){
        System.out.println("Clean up");
    }

@Nested
    class Normaltest{
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

        @Test
        @DisplayName("Should create contact on mac os only")
        @EnabledOnOs(value = OS.MAC)
        public void shouldCreateContactOnMackOSOnly(){
            contactManager.addContact("Tarikul", "Islam", "0987654680");
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
            Assertions.assertTrue(contactManager.getAllContacts().stream().filter(
                            contact -> contact.getFirstName().equals("Tarikul") &&
                                    contact.getLastName().equals("Islam") &&
                                    contact.getPhoneNumber().equals("0987654680")
                    )
                    .findAny()
                    .isPresent());
        }

    }

    @Nested
    class RepetedTest{
        @Test
        @DisplayName("Should Run on dev env only")
        public void shouldRunOnDev(){
            Assumptions.assumeTrue("TEST".equals(System.getProperty("ENV")));
            contactManager.addContact("Tarikul", "Islam", "0987654680");
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }
        // Repeated test
        @DisplayName("testRepetition test")
        @RepeatedTest(value = 5, name="Repetition test of {currentRepetition} of {totalRepetitions}")
        public void testRepetition(){
            contactManager.addContact("Tarikul", "Islam", "0987654680");
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }

        // Parameterize test
        @DisplayName("parameterize Test")
        @ParameterizedTest
        @ValueSource(strings = {"0987654680", "0987654690", "0987654670"})
        public void parameterizeTest(String phoneNo){
            contactManager.addContact("Tarikul", "Islam", phoneNo);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }


    }




}

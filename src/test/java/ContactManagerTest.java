import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContactManagerTest {

    @Test
    public void shouldCreateContact(){
        ContactManager contactManager = new ContactManager();
        contactManager.addContact("Tarikul", "Islam", "0987654680");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should Fail with null firstname")
    public void shouldNotCreateContactWhenFirstNameNull(){
        ContactManager contactManager = new ContactManager();

        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Islam", "0987654680");
        });
    }

    @Test
    @DisplayName("Should Fail with null lastname")
    public void shouldNotCreateContactWhenLastNameNull(){
        ContactManager contactManager = new ContactManager();

        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Tarikul", null, "0987654680");
        });
    }

    @Test
    @DisplayName("Should Fail with null phone no")
    public void shouldNotCreateContactWhenPhoneNumberNull(){
        ContactManager contactManager = new ContactManager();

        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Tarikul", "Islam", null);
        });
    }

}

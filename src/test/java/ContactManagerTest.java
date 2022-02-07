import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactManagerTest {

    @Test
    public void shouldCreateContact(){
        ContactManager contactManager = new ContactManager();
        contactManager.addContact("Tarikul", "Islam", "0987654680");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }
}

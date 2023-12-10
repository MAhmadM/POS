package tests;

import BusinessLayer.Role;
import BusinessLayer.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void logIn() {
        User user = new User();
        user.setName("username");
        user.setPassword("password");
        user.setRole(Role.MANAGER);
        user.save();
        User loggedInUser = user.logIn("username", "password");
        assertNotNull(loggedInUser);
    }

    @Test
    void save() {
        User user = new User();
        boolean saved = user.save();
        assertTrue(saved);
    }

    @Test
    void getName() {
        User user = new User();
        user.setName("TestUser");
        assertEquals("TestUser", user.getName());
    }

    @Test
    void setName() {
        User user = new User();
        user.setName("TestUser");
        assertEquals("TestUser", user.getName());
    }

    @Test
    void getPassword() {
        User user = new User();
        user.setPassword("TestPassword");
        assertEquals("TestPassword", user.getPassword());
    }

    @Test
    void setPassword() {
        User user = new User();
        user.setPassword("TestPassword");
        assertEquals("TestPassword", user.getPassword());
    }

    @Test
    void getRole() {
        User user = new User();
        user.setRole(Role.SALES_ASSISTANT);
        assertEquals(Role.SALES_ASSISTANT, user.getRole());
    }

    @Test
    void setRole() {
        User user = new User();
        user.setRole(Role.MANAGER);
        assertEquals(Role.MANAGER, user.getRole());
    }
}

package dev.bakku.daggerdiexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void shouldAuthenticateUser() {
        var authService = DaggerTestUserApplication.create().authService();
        var result = authService.authUser("test", "test");

        assertTrue(result.isPresent());
        assertEquals(18, result.get().getAge());
    }
}

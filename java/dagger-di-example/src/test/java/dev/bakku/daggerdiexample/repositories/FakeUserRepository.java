package dev.bakku.daggerdiexample.repositories;

import dev.bakku.daggerdiexample.models.User;

import javax.inject.Inject;
import java.util.List;

public class FakeUserRepository implements UserRepository {
    private final List<User> testData = List.of(
        new User("test", "test", 18)
    );

    @Inject
    public FakeUserRepository() {
    }

    @Override
    public List<User> getAllUsers() {
        return testData;
    }
}

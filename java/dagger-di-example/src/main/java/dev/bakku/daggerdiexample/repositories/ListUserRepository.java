package dev.bakku.daggerdiexample.repositories;

import dev.bakku.daggerdiexample.models.User;

import javax.inject.Inject;
import java.util.List;

public class ListUserRepository implements UserRepository {
    private static List<User> data = List.of(
        new User("Paul", "secret", 18),
        new User("Steven", "password", 20)
    );

    @Inject
    public ListUserRepository() {}

    @Override
    public List<User> getAllUsers() {
        return data;
    }
}

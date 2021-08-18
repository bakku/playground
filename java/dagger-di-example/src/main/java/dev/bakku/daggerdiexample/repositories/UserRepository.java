package dev.bakku.daggerdiexample.repositories;

import dev.bakku.daggerdiexample.models.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
}

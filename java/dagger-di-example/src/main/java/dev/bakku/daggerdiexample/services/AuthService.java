package dev.bakku.daggerdiexample.services;

import dev.bakku.daggerdiexample.models.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> authUser(String username, String password);
}

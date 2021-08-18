package dev.bakku.daggerdiexample.services;

import dev.bakku.daggerdiexample.models.User;
import dev.bakku.daggerdiexample.repositories.UserRepository;

import javax.inject.Inject;
import java.util.Optional;

public class RepositoryAuthService implements AuthService {
    private final UserRepository userRepository;

    @Inject
    public RepositoryAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> authUser(String username, String password) {
        return userRepository.getAllUsers()
            .stream()
            .filter(u -> u.getUsername().equals(username) && u.passwordMatches(password))
            .findFirst();
    }
}

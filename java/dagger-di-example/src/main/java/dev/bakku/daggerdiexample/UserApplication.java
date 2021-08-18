package dev.bakku.daggerdiexample;

import dagger.Component;
import dev.bakku.daggerdiexample.repositories.RepositoryModule;
import dev.bakku.daggerdiexample.services.AuthService;
import dev.bakku.daggerdiexample.services.ServiceModule;

@Component(modules = {
    RepositoryModule.class,
    ServiceModule.class
})
public interface UserApplication {
    AuthService authService();
}


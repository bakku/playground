package dev.bakku.daggerdiexample;

import dagger.Component;
import dev.bakku.daggerdiexample.repositories.FakeRepositoryModule;
import dev.bakku.daggerdiexample.services.AuthService;
import dev.bakku.daggerdiexample.services.ServiceModule;

@Component(modules = {
    ServiceModule.class,
    FakeRepositoryModule.class
})
public interface TestUserApplication extends UserApplication {
}

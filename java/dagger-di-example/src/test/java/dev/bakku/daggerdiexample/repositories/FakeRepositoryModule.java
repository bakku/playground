package dev.bakku.daggerdiexample.repositories;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class FakeRepositoryModule {
    @Binds
    public abstract UserRepository bindUserRepository(FakeUserRepository userRepository);
}

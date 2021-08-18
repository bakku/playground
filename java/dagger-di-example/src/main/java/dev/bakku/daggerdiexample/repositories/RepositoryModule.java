package dev.bakku.daggerdiexample.repositories;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {
    @Binds
    public abstract UserRepository bindUserRepository(ListUserRepository repository);
}

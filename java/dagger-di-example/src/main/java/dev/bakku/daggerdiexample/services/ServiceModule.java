package dev.bakku.daggerdiexample.services;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ServiceModule {
    @Binds
    public abstract AuthService bindAuthService(RepositoryAuthService repositoryAuthService);
}

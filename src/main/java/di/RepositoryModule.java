package di;

import com.google.inject.AbstractModule;
import services.Repository;
import services.RepositoryDB;

public class RepositoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Repository.class).to(RepositoryDB.class);
    }
}

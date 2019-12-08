package di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import entity.*;
import services.Repository;
import services.RepositoryDB;

public class RepositoryModule extends AbstractModule {
    @Override
    protected void configure() {
//        bind(Repository.class).to(RepositoryDB.class);
//        bind(new Repository(Class){})
//                .to(new RepositoryDB(Class){});
        bind(new TypeLiteral<Repository<CommandEntity>>(){})
                .to(new TypeLiteral<RepositoryDB<CommandEntity>>(){}).in(Scopes.SINGLETON);
        bind(new TypeLiteral<Repository<FieldEntity>>(){})
                .to(new TypeLiteral<RepositoryDB<FieldEntity>>(){}).in(Scopes.SINGLETON);
        bind(new TypeLiteral<Repository<IssueEntity>>(){})
                .to(new TypeLiteral<RepositoryDB<IssueEntity>>(){}).in(Scopes.SINGLETON);
        bind(new TypeLiteral<Repository<PupilEntity>>(){})
                .to(new TypeLiteral<RepositoryDB<PupilEntity>>(){}).in(Scopes.SINGLETON);
        bind(new TypeLiteral<Repository<TaskEntity>>(){})
                .to(new TypeLiteral<RepositoryDB<TaskEntity>>(){}).in(Scopes.SINGLETON);
        bind(new TypeLiteral<Repository<TeacherEntity>>(){})
                .to(new TypeLiteral<RepositoryDB<TeacherEntity>>(){}).in(Scopes.SINGLETON);

    }
}

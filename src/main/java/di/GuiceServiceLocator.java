package di;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceServiceLocator implements ServiceLocator {

    private Injector injector;

    public GuiceServiceLocator()
    {
         this.injector = Guice.createInjector(new RepositoryModule());
    }

    @Override
    public Object getInstance(Class clazz) {
        return this.injector.getInstance(clazz);
    }
}

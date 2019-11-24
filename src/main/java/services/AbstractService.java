package services;

import com.google.inject.Inject;

public abstract class AbstractService {
    protected Repository repository;
    @Inject
    AbstractService(Repository repository)
    {
        this.repository = repository;
    }
}

package services;

import com.google.inject.Inject;

public abstract class AbstractService {
    protected Class[] needRepositories = {};
    protected Repository repository;
    @Inject
    AbstractService(Repository repository)
    {
        this.repository = repository;
    }
}

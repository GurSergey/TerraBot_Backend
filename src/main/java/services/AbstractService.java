package services;

public abstract class AbstractService {
    protected Repository repository;
    AbstractService(Repository repository)
    {
        this.repository = repository;
    }
}

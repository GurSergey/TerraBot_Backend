package services;

import entity.ImageEntity;

public class RepositoryFileImage<T extends ImageEntity> implements Repository<T> {
    private static final String PATH = "avatars/";
    @Override
    public void save(T entity) {

    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public T findById(int id) {
        return null;
    }

    @Override
    public QueryBuilder getBuilderQuery() {
        return null;
    }
}

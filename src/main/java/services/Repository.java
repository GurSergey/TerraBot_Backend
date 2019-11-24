package services;

import entity.AbstractEntity;
import entity.EntityHibernate;

public interface Repository<T extends AbstractEntity> {
    public void save(T entity);
    public void update(T entity);
    public void delete(T entity);
    public AbstractEntity findById(int id);
}

package services;

import entity.AbstractEntity;
import entity.EntityHibernate;
import org.hibernate.criterion.Restrictions;

public interface Repository<T > {
    public void save(T entity);
    public void update(T entity);
    public void delete(T entity);
    public T findById(int id);
    public QueryBuilder getBuilderQuery();
}

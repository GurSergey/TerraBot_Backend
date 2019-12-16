package services;
import com.google.inject.Inject;
import entity.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Array;
import java.util.List;

public class RepositoryDB <T extends AbstractEntity> implements Repository<T> {


    private class QueryBuilderDB implements QueryBuilder {

        CriteriaQuery<T> currentQuery;
        CriteriaBuilder currentBuilder;
        Root currentRoot;

        @Override
        public QueryBuilder select() {
            currentBuilder = currentSession.getCriteriaBuilder();
            currentQuery = currentBuilder.createQuery(clazz);
            currentRoot = currentQuery.from(clazz);
            currentQuery.select(currentRoot);
            return this;
        }

        @Override
        public QueryBuilder where(SpecificationCriterion criterion) {
            currentQuery.where(currentBuilder.and(currentBuilder.equal(currentRoot.get(criterion.nameField), criterion.value)));
            return this;
        }

        @Override
        public QueryBuilder like(SpecificationCriterion criterion) {
            currentQuery.where(currentBuilder.like(currentRoot.get(criterion.nameField),
                    "%"+criterion.value+"%"));
            return this;
        }

        @Override
        public Object getObject() {
            Query<T> q = currentSession.createQuery(currentQuery);
            return q.getSingleResult();
        }

        private T[] objects;

        public T[] getObjects()
        {
            TypedQuery<T> q = currentSession.createQuery(currentQuery);
            List<T> list = q.getResultList();
            @SuppressWarnings("unchecked")
            final T[] objects = (T[]) Array.newInstance(clazz, list.size());
            for (int i = 0; i < objects.length; i++)
            {
                objects[i] = list.get(i);
            }

            return objects;
        }
    }


    private Class<T> clazz;
    private Session currentSession;

    public RepositoryDB(Class<T> type)
    {
        clazz = type;
        currentSession = HibernateSessionFactory.getSessionFactory().openSession();
    }

    public RepositoryDB()
    {

    }

    @Override
    public void finalize() {
        currentSession.close();
    }

    @Override
    public void save(T entity) {
        Transaction transaction = currentSession.beginTransaction();
        currentSession.save(entity);
        transaction.commit();
    }

    @Override
    public void update(T entity) {
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.update(entity);
            transaction.commit();
        }
        catch (PersistenceException e)
        {
            transaction = currentSession.beginTransaction();
            currentSession.merge(entity);
            transaction.commit();
        }
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(entity);
        transaction.commit();
    }

    @Override
    public T findById(int id) {
        return (T) currentSession.get(clazz, id);
    }

   public QueryBuilder getBuilderQuery()
   {
       return new QueryBuilderDB();
   }


}

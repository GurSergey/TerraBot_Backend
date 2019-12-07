package services;
import com.google.inject.Inject;
import entity.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
            currentQuery.where(currentBuilder.equal(currentRoot.get(criterion.nameField), criterion.value));
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

        public Object[] getObjects()
        {
            Query<T> q = currentSession.createQuery(currentQuery);
            return q.getResultList().toArray();
        }
    }


    private Class<T> clazz;
    private Session currentSession;
    @Inject
    public RepositoryDB(Class<T> type)
    {
        clazz = type;
    }

    public RepositoryDB()
    {
        currentSession = HibernateSessionFactory.getSessionFactory().openSession();
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
        currentSession.update(entity);
        transaction.commit();
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

package services;
import com.google.inject.Inject;
import entity.AbstractEntity;
import entity.EntityHibernate;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositoryDB <T extends AbstractEntity> implements Repository<T> {

    private Class<T> clazz;
    @Inject
    public RepositoryDB(Class<T> type)
    {
        clazz = type;
    }



//    @Override
//    public void save(AbstractEntity entity) {
//
//    }
//
//
//    @Override
//    public void update(AbstractEntity entity) {
//
//    }
//    @Override
//    public void delete(AbstractEntity entity) {
//
//    }


    @Override
    public void save(T entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(T entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(T entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public T findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        return (T) session.get(clazz, id);
    }

    @Override
    public Object specificObject(SpecificationCriterion[] criterias) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria crit = session.createCriteria(clazz);
//        for (SpecificationCriterion criterion :criterias
//             ) {
//            crit.add(Restrictions.eq(criterion.nameField, criterion.value));
//        }

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.select(root).where(builder.equal(root.get(criterias[0].nameField), criterias[0].value));
        Query<T> q=session.createQuery(query);
        return q.getSingleResult();

//        if(crit.list().size()!=0)
//            return crit.list().get(0);
//        else
//            return null;
    }

}

package services;
import entity.EntityHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepositoryDB<T extends EntityHibernate> {

    private Class<T> clazz;
    public RepositoryDB(Class<T> type)
    {
        clazz = type;
    }

    public T findById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(clazz, id);
    }

    public void save(T entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    public void update(T entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    public void delete(T entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

}

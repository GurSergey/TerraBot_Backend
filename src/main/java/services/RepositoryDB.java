package services;
import entity.AbstractEntity;
import entity.EntityHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepositoryDB implements Repository {

//    private Class<T> clazz;
//    public RepositoryDB(Class<T> type)
//    {
//        clazz = type;
//    }



    @Override
    public void save(AbstractEntity entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }
    @Override
    public void update(AbstractEntity entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }
    @Override
    public void delete(AbstractEntity entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public AbstractEntity findById(int id) {
        return null;
    }

}

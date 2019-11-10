package services;

import entity.PupilEntity;
import org.hibernate.Session;

public class PupilRegisterService {
    public void register(PupilEntity pupil){
        pupil.avatar = "123";
        pupil.name ="123";
        pupil.password = "123";
        pupil.token = "123";
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        //Transaction tx1 = session.beginTransaction();
        session.save(pupil);
        //tx1.commit();
        session.close();
    }

}

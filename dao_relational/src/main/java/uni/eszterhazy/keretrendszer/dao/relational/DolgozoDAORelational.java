package uni.eszterhazy.keretrendszer.dao.relational;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import uni.eszterhazy.keretrendszer.dao.DolgozoDAO;
import uni.eszterhazy.keretrendszer.model.Dolgozo;
import uni.eszterhazy.keretrendszer.model.Reszleg;

import java.util.Collection;

public class DolgozoDAORelational implements DolgozoDAO {
    private static SessionFactory factory;

    public DolgozoDAORelational() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void createDolgozo(Dolgozo dolgozo) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(dolgozo);
        tx.commit();
        session.close();
    }

    @Override
    public Collection<Dolgozo> readAllDolgozo() {
        Session session = factory.openSession();
        Collection<Dolgozo> result = session.createQuery("FROM Dolgozo").list();
        return result;
    }

    @Override
    public Dolgozo readDolgozo(String id) {
        Session session= factory.openSession();
        Dolgozo result = session.get(Dolgozo.class, id);
        return result;
    }

    @Override
    public void updateDolgozo(Dolgozo dolgozo) {

    }

    @Override
    public void deleteDolgozo(Dolgozo dolgozo) {

    }

    @Override
    public Collection<Dolgozo> readAllDolgozoOfReszleg(Reszleg reszleg) {
        Session session = factory.openSession();
        String hql = "From Dolgozo Where reszleg = :reszleg";
        Query q = session.createQuery(hql);
        q.setParameter("reszleg",reszleg);
        Collection result = q.list();
        return result;
    }
}

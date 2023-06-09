package m4final.maslennikov.dao;

import m4final.maslennikov.domain.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CountryDAO extends GenericWorldDao<Country> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }

    @Override
    public List<Country> getAll() {
        String hql = "select c from Country c join fetch c.languages";
        try(Session session = getCurrentSession().getSessionFactory().openSession()){
            return session.createQuery(hql, Country.class).list();
        }
    }
}

package m4final.maslennikov.dao;

import m4final.maslennikov.domain.Country;
import org.hibernate.SessionFactory;

public class CountryDAO extends GenericWorldDao<Country> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}

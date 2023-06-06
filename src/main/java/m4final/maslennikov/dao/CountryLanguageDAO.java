package m4final.maslennikov.dao;

import m4final.maslennikov.domain.CountryLanguage;
import org.hibernate.SessionFactory;

public class CountryLanguageDAO extends GenericWorldDao<CountryLanguage> {
    public CountryLanguageDAO(SessionFactory sessionFactory) {
        super(CountryLanguage.class, sessionFactory);
    }
}

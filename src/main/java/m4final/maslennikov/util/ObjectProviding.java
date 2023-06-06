package m4final.maslennikov.util;

import m4final.maslennikov.config.XMLSessionFactoryProvider;
import m4final.maslennikov.dao.CityDao;
import m4final.maslennikov.dao.CountryDAO;
import m4final.maslennikov.dao.CountryLanguageDAO;
import m4final.maslennikov.redis.Redis;
import org.hibernate.SessionFactory;

public class ObjectProviding {
    public static SessionFactory sessionFactory = new XMLSessionFactoryProvider().getSessionFactory();
    public static CityDao citiDao = new CityDao(sessionFactory);
    public static CountryDAO countryDAO = new CountryDAO(sessionFactory);
    public static CountryLanguageDAO countryLanguageDAO = new CountryLanguageDAO(sessionFactory);
    public static Redis redis = new Redis();
    public static RuntimeTesting runtimeTesting = new RuntimeTesting();
}

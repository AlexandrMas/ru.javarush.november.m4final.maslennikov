package m4final.maslennikov.dao;

import m4final.maslennikov.domain.City;
import m4final.maslennikov.domain.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

import static m4final.maslennikov.util.ObjectProviding.countryDAO;

public class CityDao extends GenericWorldDao<City> {
    public CityDao(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public List<City> fetchData() {
        try (Session session = this.getCurrentSession().getSessionFactory().openSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();
            List<Country> countries = countryDAO.getAll();
            int totalCount = getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }
}

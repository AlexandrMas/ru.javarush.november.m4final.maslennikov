package m4final.maslennikov.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import m4final.maslennikov.domain.City;
import m4final.maslennikov.domain.CountryLanguage;
import m4final.maslennikov.redis.CityCountry;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;

import static m4final.maslennikov.util.ObjectProviding.*;

public class RuntimeTesting {

    public void testRedisData(List<Integer> ids) {
        try (StatefulRedisConnection<String, String> connection = redis.prepareRedisClient().connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (Integer id : ids) {
                String value = sync.get(String.valueOf(id));
                try {
                    redis.getMapper().readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void testMysqlData(List<Integer> ids) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            for (Integer id : ids) {
                City city = citiDao.getById(id);
                Set<CountryLanguage> languages = city.getCountry().getLanguages();
            }
            session.getTransaction().commit();
        }
    }
}

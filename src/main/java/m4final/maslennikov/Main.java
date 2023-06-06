package m4final.maslennikov;

import m4final.maslennikov.domain.City;
import m4final.maslennikov.redis.CityCountry;

import java.util.List;

import static m4final.maslennikov.liquibase.Validator.validate;
import static m4final.maslennikov.util.Converter.transformCitiesToCitiCountries;
import static m4final.maslennikov.util.ObjectProviding.*;

public class Main {
    public static void main(String[] args) {

        validate();

        List<City> cities = citiDao.fetchData();
        List<CityCountry> cityCountries = transformCitiesToCitiCountries(cities);

        redis.pushToRedis(cityCountries);

        sessionFactory.getCurrentSession().close();

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        runtimeTesting.testRedisData(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        runtimeTesting.testMysqlData(ids);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));
    }
}

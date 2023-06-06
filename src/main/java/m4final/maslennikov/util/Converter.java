package m4final.maslennikov.util;

import m4final.maslennikov.domain.City;
import m4final.maslennikov.domain.Country;
import m4final.maslennikov.domain.CountryLanguage;
import m4final.maslennikov.redis.CityCountry;
import m4final.maslennikov.redis.Language;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Converter {
    public static List<CityCountry> transformCitiesToCitiCountries(List<City> cities) {
        return cities.stream().map(city -> {
            CityCountry res = new CityCountry();
            res.setId(city.getId());
            res.setName(city.getName());
            res.setPopulation(city.getPopulation());
            res.setDistrict(city.getDistrict());

            Country country = city.getCountry();
            res.setAlternativeCountryCode(country.getCode2());
            res.setContinent(country.getContinent());
            res.setCountryCode(country.getCode());
            res.setCountryName(country.getName());
            res.setCountryPopulation(country.getPopulation());
            res.setCountryRegion(country.getRegion());
            res.setCountrySurfaceArea(country.getSurfaceArea());

            Set<CountryLanguage> countryLanguages = country.getLanguages();
            Set<Language> languages = countryLanguages.stream().map(cl -> {
                Language language = new Language();
                language.setLanguage(cl.getLanguage());
                language.setOfficial(cl.getOfficial());
                language.setPercentage(cl.getPercentage());
                return language;
            }).collect(Collectors.toSet());
            res.setLanguages(languages);
            return res;
        }).toList();
    }
}

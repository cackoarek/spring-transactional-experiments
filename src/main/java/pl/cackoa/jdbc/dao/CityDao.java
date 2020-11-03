package pl.cackoa.jdbc.dao;

import pl.cackoa.jdbc.model.City;

public interface CityDao extends AbstractDao<City> {

  City findByName(String name);

}

package pl.cackoa.jdbc.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.cackoa.jdbc.dao.CityDao;
import pl.cackoa.jdbc.model.City;

/**
 * Created by acacko on 22.01.20
 */

@Transactional
@Repository
public class CityRepository extends AbstractRepository<City> implements CityDao {

  public CityRepository() {
    super(City.class);
  }

  @Override
  public City findByName(String name) {
    return entityManager.createQuery("from " + clazz.getName() + " where name=:name", clazz)
        .setParameter("name", name)
        .getSingleResult();
  }


}

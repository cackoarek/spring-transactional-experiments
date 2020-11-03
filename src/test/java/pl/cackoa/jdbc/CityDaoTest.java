package pl.cackoa.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.cackoa.jdbc.dao.CityDao;
import pl.cackoa.jdbc.model.City;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class CityDaoTest {

  @Autowired
  CityDao cityDao;

  @Test
  public void anyTest() {

    Assert.assertTrue(true);
    assertThat(true).isTrue();

  }

  @Test
  public void saveTest() {
    //given
    int sizeBefore = cityDao.findAll().size();

    City newCity = new City();
    newCity.setName("Kraków");

    //when
    cityDao.save(newCity);

    //then
    int sizeAfter = cityDao.findAll().size();
    Assert.assertTrue(true);
    assertThat(sizeAfter).isGreaterThan(sizeBefore);
    assertThat(sizeAfter).isEqualTo(sizeBefore + 1);

  }

  @Test
  public void getFirst() {
    //given
    int sizeBefore = cityDao.findAll().size();

    City newCity = new City();
    newCity.setName("Kraków");

    //when
    cityDao.save(newCity);

    //then
    int sizeAfter = cityDao.findAll().size();
    Assert.assertTrue(true);
    assertThat(sizeAfter).isGreaterThan(sizeBefore);
    assertThat(sizeAfter).isEqualTo(sizeBefore + 1);

  }

}

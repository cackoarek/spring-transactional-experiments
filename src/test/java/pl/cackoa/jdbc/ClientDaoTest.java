package pl.cackoa.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.cackoa.jdbc.dao.ClientDao;
import pl.cackoa.jdbc.model.Client;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class ClientDaoTest {

  @Autowired
  ClientDao myClientDao;

  @Test
  public void anyTest() {

    Assert.assertTrue(true);
    assertThat(true).isTrue();

    assertThat(!myClientDao.findAll().isEmpty());
    assertThat(myClientDao.findByFirstName("Norbert").isEmpty());
    assertThat(myClientDao.findByLastName("Skorupa").size() == 1);
    assertThat(myClientDao.findById(1L).getLast_name().startsWith("Tom"));
  }

  @Test
  public void saveTest() {
    //given
    int sizeBefore = myClientDao.findAll().size();

    Client newClient = new Client();
    newClient.setFirst_name("Bolek");
    newClient.setLast_name("Lolek" + (sizeBefore + 1));

    //when
    myClientDao.save(newClient);

    //then
    int sizeAfter = myClientDao.findAll().size();
    Assert.assertTrue(true);
    assertThat(sizeAfter).isGreaterThan(sizeBefore);
    assertThat(sizeAfter).isEqualTo(sizeBefore + 1);

  }
}

package pl.cackoa.jdbc.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.cackoa.jdbc.dao.ClientDao;
import pl.cackoa.jdbc.model.Client;

import java.util.List;


@Transactional
@Repository
public class ClientRepository extends AbstractRepository<Client> implements ClientDao {

  public ClientRepository() {
    super(Client.class);
  }

  @Override
  public List<Client> findByFirstName(String name) {
    return entityManager.createQuery("from " + clazz.getName() + " where first_name=:name", clazz)
            .setParameter("name", name)
            .getResultList();
  }

  @Override
  public List<Client> findByLastName(String name) {
    return entityManager.createQuery("from " + clazz.getName() + " where last_name=:name", clazz)
            .setParameter("name", name)
            .getResultList();
  }
  
}

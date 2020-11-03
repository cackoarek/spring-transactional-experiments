package pl.cackoa.jdbc.dao;

import pl.cackoa.jdbc.model.Client;

import java.util.List;

public interface ClientDao extends AbstractDao<Client> {
  List<Client> findByFirstName(String name);
  List<Client> findByLastName(String name);
}

package pl.cackoa.jdbc.dao;

import java.util.List;

import pl.cackoa.jdbc.model.AbstractEntity;

public interface AbstractDao<T extends AbstractEntity> {

  T findById(Long id);

  void remove(T entity);

  T update(T entity);

  void save(T entity);

  void databaseOperationWithException(T entity);

  List<T> findAll();
}

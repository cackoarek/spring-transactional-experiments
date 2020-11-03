package pl.cackoa.jdbc.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import pl.cackoa.jdbc.dao.AbstractDao;
import pl.cackoa.jdbc.model.AbstractEntity;

/**
 * Created by acacko on 23.01.20
 */
@Transactional
public abstract class AbstractRepository<T extends AbstractEntity> implements AbstractDao<T> {

  @PersistenceContext
  protected EntityManager entityManager;

  protected Class<T> clazz;

  public AbstractRepository(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public T findById(Long id) {
    return entityManager.find(clazz, id);
  }

  // in JPA there isn't any UPDATE. Simply flush or merge
  @Override
  public T update(T entity) {
    entityManager.flush();
    return entityManager.contains(entity) ? entity : entityManager.merge(entity);
//        or:
//        entityManager.flush();
//        return entity;
  }


  @Override
  public void remove(T entity) {
    entityManager.remove(entity);
  }

  @Override
  public void save(T entity) {
    entityManager.persist(entity);
  }

  @Override
  public void databaseOperationWithException(T entity) {
    save(entity);
    throw new RuntimeException("Błąd bazy danych");
  }

  @Override
  public List<T> findAll() {
    return entityManager.createQuery("from " + clazz.getName(), clazz).getResultList();
  }
}

package pl.cackoa.jdbc.sevices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cackoa.jdbc.dao.CityDao;
import pl.cackoa.jdbc.dao.ClientDao;
import pl.cackoa.jdbc.model.City;
import pl.cackoa.jdbc.model.Client;

import java.util.UUID;

@AllArgsConstructor
@Service
public class SystemService {

  private CityDao cityDao;
  private ClientDao clientDao;

  /**
   * Add 2 entities. All should be accepted. No exception
   */
  @Transactional
  public int positiveScenario() {
    addCity();
    addClient();
    return countAll();
  }

  /**
   * Add 2 entities in SEPARATE transactions.
   * Exception is thrown in last operation.
   * Only one should be rollbacked.
   */
  public int writeWithClientException() {
    try {
      addCityTransactional();
      addClientTransactionalWithException();
    } catch (Exception ignored) {
    }
    return countAll();
  }

  /**
   * Add 2 entities in SAME transactions. Exception is thrown.
   * All changes should be rollbacked.
   */
  @Transactional
  public void addWithExceptionInSameTransaction() {
    addCity();
    clientDao.databaseOperationWithException(randomClient());
  }

  /**
   * Add 2 entities in SAME transaction.
   * Transactions are separated in other thread.
   * No exception is thrown.
   * All changes should be commited.
   */
  @Transactional
  public void addWithTransactionSeparation() {
    addCityTransactional();
    TransactionSeparator.executeWithNoResult(this::addClientTransactional);
  }

  /**
   * Add 2 entities in SAME transaction.
   * Transactions are separated in other thread.
   * Exception is thrown in last operation.
   * Only last modification should be rollbacked.
   */
  @Transactional
  public void addWithExceptionWithTransactionSeparation() {
    addCityTransactional();
    TransactionSeparator.executeWithNoResult(this::addClientTransactionalWithException);
  }

  /**
   * Add 2 entities in SAME transaction.
   * Transactions are separated in other thread.
   * Exception is thrown in last operation.
   * Only last modification should be rollbacked.
   */
  @Transactional
  public void addWithExceptionWithTransactionSeparation2() {
    TransactionSeparator.executeWithNoResult(this::addCity);
    clientDao.databaseOperationWithException(randomClient());
  }


  public void addCity() {
    String randomName = UUID.randomUUID().toString();
    cityDao.save(new City(randomName));
  }

  @Transactional
  public void addCityTransactional() {
    addCity();
  }

  public void addClient() {
    String firstName = UUID.randomUUID().toString();
    String lastName = UUID.randomUUID().toString();
    clientDao.save(new Client(firstName, lastName));
  }

  @Transactional
  public void addClientTransactional() {
    addClient();
  }

  @Transactional
  public void addClientTransactionalWithException() {
    clientDao.databaseOperationWithException(randomClient());
  }

  public int countAll() {
    return cityDao.findAll().size() + clientDao.findAll().size();
  }

  private Client randomClient() {
    String firstName = UUID.randomUUID().toString();
    String lastName = UUID.randomUUID().toString();
    return new Client(firstName, lastName);
  }


}

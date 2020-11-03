package pl.cackoa.jdbc.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.cackoa.jdbc.App;
import pl.cackoa.jdbc.dao.CityDao;
import pl.cackoa.jdbc.dao.ClientDao;
import pl.cackoa.jdbc.sevices.SystemService;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Transaction experiments with Spring @Transactional annotation.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class ServiceTest {

    @Autowired
    SystemService systemService;

    @Autowired
    CityDao cityDao;

    @Autowired
    ClientDao clientDao;

    int sum;

    @Before
    public void before() {
        sum = systemService.countAll();
    }

    /**
     * Add 2 entities. All should be accepted. No exception
     */
    @Test
    public void positiveScenario() {
        //when
        int sumAfterAdd = systemService.positiveScenario();

        //then
        assertThat(sumAfterAdd).isEqualTo(sum + 2);
    }

    /**
     * Add 2 entities in SEPARATE transactions.
     * Exception is thrown in last operation.
     * Only one should be rollbacked.
     */
    @Test
    public void withClientExceptionSeparateTransactions() {
        //when
        int sumAfterAdd = systemService.writeWithClientException();

        //then
        assertThat(sumAfterAdd).isEqualTo(sum + 1);
    }

    /**
     * Simple Transactional Rollback test.
     */
    @Test
    public void withClientExceptionSameTransaction() {
        // given
        boolean exception = false;

        //when
        try {
            systemService.addClientTransactionalWithException();
        } catch (Exception e) {
            exception = true;
        }

        //then
        assertThat(systemService.countAll()).isEqualTo(sum);
        assertThat(exception).isTrue();
    }

    /**
     * Add 2 entities in SAME transactions. Exception is thrown.
     * All changes should be rollbacked.
     */
    @Test
    public void withClientExceptionSameTransaction2() {
        //when
        try {
            systemService.addWithExceptionInSameTransaction();
        } catch (Exception ignored) {}

        //then
        assertThat(systemService.countAll()).isEqualTo(sum);
    }


    /**
     * Add 2 entities in SAME transaction.
     * Transactions are separated in other thread.
     * No exception is thrown.
     * All changes should be commited.
     */
    @Test
    public void withTransactionSeparation() {
        // given

        //when
        systemService.addWithTransactionSeparation();

        //then
        assertThat(systemService.countAll()).isEqualTo(sum + 2);
    }

    /**
     * Add 2 entities in SAME transaction.
     * Transactions are separated in other thread.
     * Exception is thrown in last operation.
     * Only last modification should be rollbacked.
     */
    @Test
    public void withTransactionSeparationWithException() {
        // given

        //when
        systemService.addWithExceptionWithTransactionSeparation();

        //then
        assertThat(systemService.countAll()).isEqualTo(sum + 1);
    }

    /**
     * Add 2 entities in SAME transaction.
     * Transactions are separated in other thread.
     * Exception is thrown in last operation.
     * Only last modification should be rollbacked.
     */
    @Test
    public void withTransactionSeparationWithException2() {
        // given
        int citySize = cityDao.findAll().size();
        int clientSize = clientDao.findAll().size();

        //when
        try {
            systemService.addWithExceptionWithTransactionSeparation2();
        } catch (Exception ignored) {
            System.out.println("Exception!");
        }

        //then
        assertThat(systemService.countAll()).isEqualTo(sum + 1);
        assertThat(cityDao.findAll().size()).isEqualTo(citySize + 1);
        assertThat(clientDao.findAll().size()).isEqualTo(clientSize);
    }


}

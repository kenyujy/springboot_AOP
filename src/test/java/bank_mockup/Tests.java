package bank_mockup;

import bank_mockup.bank_acc_service.BankAccService;
import bank_mockup.entity_model.BankAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {

    @Autowired
    BankAccService bankAccService;

    @Autowired
    DataSourceTransactionManager transactionManager;

    @Test
    public void testUpdateBalance() {
        bankAccService.updateAccBalance(-100,1);
    }

    @Test
    public void findAccByName() {
        BankAccount acc=bankAccService.findAccByName("渣渣辉");
        System.out.println(acc);
    }

    @Test
    public void bankTransfer() {
        TransactionStatus txStatus =
                transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            bankAccService.updateAccBalance(-100, 1);
            //int i = 1 / 0;
            bankAccService.updateAccBalance(100, 2);

        }catch (Exception e){
            transactionManager.rollback(txStatus);  //有问题回滚
            throw e;
        }finally {
            transactionManager.commit(txStatus);   //没问题就提交
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bankTransfer2() throws InterruptedException {

        String msg= bankAccService.bankTransfer("渣渣辉","吴莎莲",100);
        System.out.println(msg);
    }

    @Test
    public void logtest() {
        Logger logger= LoggerFactory.getLogger(getClass());
        //日志级别 trace< debug< info < warn < error
        // 默认日志级别是info， 需要把日志级别调成trace 才能看到所有的日志
        logger.trace("这是trace");
        logger.debug("这是debug");
        logger.info("这是info");
        logger.warn("这是warn");
        logger.error("这是error");
    }

}

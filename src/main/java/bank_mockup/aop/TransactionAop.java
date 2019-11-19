package bank_mockup.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
@Aspect   // AOP注解，表示这是一个切面类
public class TransactionAop {

    @Pointcut("execution(public String bank_mockup.bank_acc_service.BankAccService.bankTransfer(String, String, double))")
    public void function1() {
    }

    @Autowired
    DataSourceTransactionManager transactionManager;

    @Around("function1()")
    public Object SqlTransaction (ProceedingJoinPoint pjp) throws Throwable{

        Object[] args= pjp.getArgs(); //获取代理方法的参数
        Object proceed= null;
        // 由 TransactionManager 来接管数据库事务？
        TransactionStatus txStatus =
                transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            proceed= pjp.proceed(args);   // 执行目标方法 (模拟bank transfer)

        } catch(Exception e){
            transactionManager.rollback(txStatus);  //有问题回滚
            throw e;
        }
        transactionManager.commit(txStatus);   //没问题就提交
        return proceed;
    }
}

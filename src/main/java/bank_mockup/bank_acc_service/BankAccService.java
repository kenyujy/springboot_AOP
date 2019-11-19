package bank_mockup.bank_acc_service;

import bank_mockup.entity_model.BankAccount;
import bank_mockup.mybatis_mapper.BankAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccService {

    @Autowired
    BankAccountMapper bankAccountMapper;

    public void updateAccBalance(double amount, long account_id){
        bankAccountMapper.updateAccBalance(amount, account_id);
    }

    public BankAccount findAccByName(String name){
        BankAccount account= bankAccountMapper.findAccByName(name);
        return account;
    }

    public String bankTransfer(String Payer, String Beneficiary, double amount){
        String msg="";
        if (amount<0){
            return msg+"汇款额必须大于0";}

        BankAccount payer= bankAccountMapper.findAccByName(Payer);
        BankAccount beneficiary= bankAccountMapper.findAccByName(Beneficiary);

        if (payer==null){
            return msg+"汇款人不存在";
        }else if(beneficiary==null){
            return msg+"收款人不存在";
        }else if (payer.getBalance()<amount){
            return msg+"账户余额不足";
        }

        bankAccountMapper.updateAccBalance(-amount, payer.getAccount_id());
        //int i=1/0;
        bankAccountMapper.updateAccBalance(amount, beneficiary.getAccount_id());
        return msg+"汇款成功";

    }
}

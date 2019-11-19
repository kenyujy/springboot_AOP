package bank_mockup.mybatis_mapper;

import bank_mockup.entity_model.BankAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Mapper
@Component
public interface BankAccountMapper {

    @Select("select * from bank_account WHERE holder_name =#{name}")
    BankAccount findAccByName(String name);

    @Select("select * from bank_account WHERE account_id= #{account_id} and balance>= #{amount} for update")
    BankAccount findAccByIdForUpdate(@Param("account_id") long account_id, @Param("amount") double amount);

    // 乐观锁更新, where 后面的语句加上 业务约束条件 balance+#{amount}>0
    @Update("update bank_account set balance=balance+#{amount} WHERE account_id=#{account_id} and balance+#{amount}>0")
    void updateAccBalance(@Param("amount") double amount, @Param("account_id") long account_id);
}

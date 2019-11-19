package bank_mockup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("bank_mockup.mybatis_mapper")//扫描Mapper, 改了以后要注意，否则bean type could not be found
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableCaching  //开启缓存  只要redis 包导入了，redis缓存就会生效， 需要配置redis服务器地址，及运行redis
@SpringBootApplication

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
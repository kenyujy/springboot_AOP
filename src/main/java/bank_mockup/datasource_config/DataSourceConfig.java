package bank_mockup.datasource_config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//requires JDBC dependency!
@Component
@Configuration
//@PropertySource(value = {"classpath:person.properties"}) //指定另外单独的配置文件
//@ConfigurationProperties (prefix = "data-source-config") //变量的名字：prefix
public class DataSourceConfig {
	
	@Value("${my.database.url}")
	private String databaseUrl;
	
	@Value("${my.database.username}")
	private String databaseUser;
	
	@Value("${my.datasource.password}")
	private String databasePassword;
	
	@Value("${my.datasource.driver-class-name}")
	private String databaseDriver;	
	
	@Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(databaseDriver);
        dataSourceBuilder.url(databaseUrl);
        dataSourceBuilder.username(databaseUser);
        dataSourceBuilder.password(databasePassword);
        return dataSourceBuilder.build();
	}

	@Bean  //返回SqlSessionFactory 并加入IOC 容器
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(getDataSource());
		return factoryBean.getObject();
	}

	@Bean //返回DataSourceTransactionManager 并加入IOC 容器，默认id 就是方法名小写
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}

}

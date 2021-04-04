package com.quan.framework.aop.mapper.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.quan.framework.aop.mapper.annotation.CustomerDB;
import com.quan.framework.aop.mapper.wrapper.MapWrapperFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 自定义数据源的数据库连接相关配置
 *
 * @author Mr.XiHui
 * @date 2019/8/27
 * @see com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.customer")
@ConditionalOnBean(DataSource.class)
@MapperScan(
        basePackages = "com.quan.**.mapper",
        annotationClass = CustomerDB.class,
        sqlSessionFactoryRef = "customerDBSqlSessionFactory"
)
public class CustomerDBConfig {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    /**
     * 根据配置的属性初始库的数据源
     *
     * @return DataSource
     */
    @Bean
    @Primary
    public DataSource customerDBDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * 根据数据源初始化事务管理器
     *
     * @return DataSource
     */
    @Bean
    @Primary
    public DataSourceTransactionManager customerDBTransactionManager(@Qualifier("customerDBDataSource")
                                                                          DataSource customerDBDataSource) {
        return new DataSourceTransactionManager(customerDBDataSource);
    }

    /**
     * 根据数据源初始化Mybatis的SqlSessionFactory
     *
     * @return DataSource
     */
    @Bean
    @Primary
    public SqlSessionFactory customerDBSqlSessionFactory(@Qualifier("customerDBDataSource")
                                                              DataSource zabbixDBDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(zabbixDBDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);
        sessionFactory.setObjectWrapperFactory(new MapWrapperFactory());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/*/*.xml"));
        return sessionFactory.getObject();
    }


    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName == null ? null : driverClassName.trim();
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}

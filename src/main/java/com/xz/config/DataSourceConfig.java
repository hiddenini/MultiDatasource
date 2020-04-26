package com.xz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xz
 * @date 2020/4/24 15:47
 * 方式一:将多数据源以bean的形式注入到spring中,感觉不是太灵活,新添加数据源需要加 @Bean(name = "xxx")
 **/
@Configuration
public class DataSourceConfig {
    //数据源1
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    //数据源2
    @Bean(name = "slave1")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    //数据源3
    @Bean(name = "slave2")
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource dataSource3() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource1());
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap();
        dsMap.put("master", dataSource1());
        dsMap.put("slave1", dataSource2());
        dsMap.put("slave2", dataSource3());
        dynamicDataSource.setTargetDataSources(dsMap);
        // 数据源上下文，用于管理数据源与记录已经注册的数据源key
        DynamicDataSourceContextHolder.dataSourceIds.add("master");
        DynamicDataSourceContextHolder.dataSourceIds.add("slave1");
        DynamicDataSourceContextHolder.dataSourceIds.add("slave2");
        return dynamicDataSource;
    }

    /**
     * is it necessary to create PlatformTransactionManager for every single dataSource?
     *
     * 如果一个方法中只操作了一个数据源,那么可以不需要配置任何的PlatformTransactionManager 因为springBoot自动配置了通过TransactionAutoConfiguration.class
     *
     * 但是 如果一个方法中只操作了多个数据源 需要分布式的事务
     *
     * 并且如果在@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, TransactionAutoConfiguration.class})
     *
     * 事务是不生效的
     */


    /**
     * 配置@Transactional注解事物
     * @return
     */
/*    @Bean("masterTransactionManager")
    public PlatformTransactionManager transactionManager1() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    @Bean("slave1TransactionManager")
    public PlatformTransactionManager transactionManager2() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    @Bean("slave2TransactionManager")
    public PlatformTransactionManager transactionManager3() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }*/

/*    @Bean
    public PlatformTransactionManager transactionManager1() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }*/
}

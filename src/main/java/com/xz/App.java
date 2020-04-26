package com.xz;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xz
 * @date 2020/4/24 16:24
 *          如果使用方式一 需要将@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})打开 注释掉@Import({DynamicDataSourceRegister.class})
            注释掉@SpringBootApplication 将application-yml 的active置为anno 打开DataSourceConfig的@Configuration

            如果使用方式二 需要将@Import({DynamicDataSourceRegister.class})和@SpringBootApplication打开 注释掉@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
            注释掉DataSourceConfig的@Configuration
            将application-yml 的active置为register
 *
 **/
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@Import({DynamicDataSourceRegister.class})
//@SpringBootApplication
@MapperScan("com.xz.mapper")
@ComponentScan(basePackages = {"com.xz.*"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

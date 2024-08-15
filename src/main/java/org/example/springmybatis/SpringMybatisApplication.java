package org.example.springmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMybatisApplication.class, args);

        DataSource dataSource = ApplicationContextUtil.getBean(DataSource.class) ;
        System.out.println("----------------------------------");
        System.out.println(dataSource.getClass().getName());
        System.out.println("----------------------------------");
    }
}

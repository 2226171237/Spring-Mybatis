package org.example.springmybatis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:spring-dao.xml")
public class ApplicationConfig {
}

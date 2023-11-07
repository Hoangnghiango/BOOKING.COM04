package com.casestudy_module4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(value = "com.casestudy_module4.repository")
//@EntityScan(value = "com.casestudy_module4.model")
public class CasestudyModule4Application {

    public static void main(String[] args) {

        SpringApplication.run(CasestudyModule4Application.class, args);
    }
    //exclude = {DataSourceAutoConfiguration.class }

}

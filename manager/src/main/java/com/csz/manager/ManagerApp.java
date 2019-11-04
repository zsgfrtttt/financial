package com.csz.manager;

import com.csz.swagger.SwaggerConfiguration;
import com.csz.swagger.SwaggerImport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.csz.entity"})
//@SwaggerImport
public class ManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApp.class);
    }
}

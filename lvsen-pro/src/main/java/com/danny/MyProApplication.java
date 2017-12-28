package com.danny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
// @EnableTransactionManagement
// @MapperScan("com.**.mapper")
 @ServletComponentScan
public class MyProApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MyProApplication.class);
        application.run(args);
        application.setLogStartupInfo(true);
    }
}

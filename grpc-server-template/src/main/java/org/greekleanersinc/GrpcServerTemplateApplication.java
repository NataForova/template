package org.greekleanersinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class GrpcServerTemplateApplication {

    public static void main(String[] args) {
       SpringApplication.run(GrpcServerTemplateApplication.class, args);
    }
}

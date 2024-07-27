package org.greekleanersinc.servicetemplate;

import org.greekleanersinc.servicetemplate.client.TemplateGrpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ServiceTemplateApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ServiceTemplateApplication.class, args);
        TemplateGrpcClient templateTest = applicationContext.getBean(TemplateGrpcClient.class);
        System.out.println("Client stared");
        templateTest.findTemplate(1L);
    }
}

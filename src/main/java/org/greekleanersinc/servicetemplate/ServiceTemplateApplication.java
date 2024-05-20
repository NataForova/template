package org.greekleanersinc.servicetemplate;

import org.greekleanersinc.servicetemplate.client.ClientClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ServiceTemplateApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ServiceTemplateApplication.class, args);
        ClientClient templateTest = applicationContext.getBean(ClientClient.class);
        templateTest.findTemplate(1L);
    }

}

package org.greekleanersinc.clienttemplate;

import org.greekleanersinc.clienttemplate.client.TemplateGrpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ClientTemplateApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ClientTemplateApplication.class, args);
        TemplateGrpcClient templateTest = applicationContext.getBean(TemplateGrpcClient.class);
        System.out.println("Client was stared");
        templateTest.findTemplate(1L);
    }

}

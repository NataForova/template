package org.greekleanersinc.grpcclient;

import org.greekleanersinc.grpcclient.client.GrpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GrpcClientApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(GrpcClientApplication.class, args);
        GrpcClient templateTest = applicationContext.getBean(GrpcClient.class);
        System.out.println("Client was stared");
        var result = templateTest.findDataById(1L);
        System.out.println("Client received example data with text " + result.getText());
    }

}

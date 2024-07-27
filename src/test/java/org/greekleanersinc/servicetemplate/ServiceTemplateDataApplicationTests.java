package org.greekleanersinc.servicetemplate;

import org.greekleanersinc.servicetemplate.client.TemplateGrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceTemplateDataApplicationTests {

    @Autowired
    TemplateGrpcClient templateGrpcClient;
    @Test
    void getTemplateDataTest() {
        templateGrpcClient.findTemplate(1L);
    }

}

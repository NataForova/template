/*
package org.greekleanersinc.servicetemplate.client;


import net.devh.boot.grpc.client.inject.GrpcClient;
import org.greekleanersinc.servicetemplate.Request;
import org.greekleanersinc.servicetemplate.Response;
import org.greekleanersinc.servicetemplate.TemplateServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClientClient {
    private static final Logger logger = LoggerFactory.getLogger(ClientClient.class);

    @GrpcClient("grpc-server")
    private TemplateServiceGrpc.TemplateServiceBlockingStub templateServiceStub;

    public void findTemplate(Long id, String message) {
        Request request = Request.newBuilder().setId(id)
                .build();
        Response helloWorldResponse = this.templateServiceStub.findTemplate(request);
        logger.info(String.format("Server sent a response: %1s", helloWorldResponse.getResponseData()));
    }

}*/

package org.greekleanersinc.servicetemplate.client;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.greekleanersinc.servicetemplate.Request;
import org.greekleanersinc.servicetemplate.Response;
import org.greekleanersinc.servicetemplate.TemplateServiceGrpc;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientClient {

    @GrpcClient("grpc-server")
    private TemplateServiceGrpc.TemplateServiceBlockingStub templateServiceStub;

    public ClientClient() {
    }

    public void findTemplate(Long id) {
        Request request = Request.newBuilder().setId(id)
                .build();
        Response helloWorldResponse = this.templateServiceStub.findTemplate(request);
        log.info(String.format("Server sent a response: %1s", helloWorldResponse.getResponseData()));
    }

}

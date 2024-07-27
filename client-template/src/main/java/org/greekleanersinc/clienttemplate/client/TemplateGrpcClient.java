package org.greekleanersinc.clienttemplate.client;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.greekleanersinc.servicetemplate.Request;
import org.greekleanersinc.servicetemplate.Response;
import org.greekleanersinc.servicetemplate.TemplateServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class TemplateGrpcClient {
    @GrpcClient("grpc-server")
    private TemplateServiceGrpc.TemplateServiceBlockingStub templateServiceStub;

    public TemplateGrpcClient() {
    }

    public Response findTemplate(Long id) {
        Request request = Request.newBuilder().setId(id)
                .build();
        return this.templateServiceStub.findTemplate(request);
    }
}

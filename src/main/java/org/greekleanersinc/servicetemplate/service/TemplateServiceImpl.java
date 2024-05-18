package org.greekleanersinc.servicetemplate.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.greekleanersinc.servicetemplate.TemplateData;
import org.greekleanersinc.servicetemplate.TemplateRequest;
import org.greekleanersinc.servicetemplate.TemplateResponse;
import org.greekleanersinc.servicetemplate.TemplateServiceGrpc;
import org.greekleanersinc.servicetemplate.model.Template;

@GrpcService
public class TemplateServiceImpl extends TemplateServiceGrpc.TemplateServiceImplBase {

    @Override
    public void findTemplate(TemplateRequest request,
                             StreamObserver<TemplateResponse> responseObserver) {
        Long id = Long.getLong(request.getId());
        //log.info("Finding template by id: {}", id);

        //var template = templateRepository.findById(id);
        TemplateResponse templateResponse = TemplateResponse
                .newBuilder()
                .setTemplate(convertToProto(new Template()))
                .build();
        responseObserver.onNext(templateResponse);
        responseObserver.onCompleted();
    }

    public static TemplateData convertToProto(Template template) {
        return TemplateData.newBuilder()
                .setId("1")
                .setName("test")
                .build();
    }

    public static Template convertToPojo(TemplateData templateData) {
        return new Template(
                Long.getLong(templateData.getId()),
                templateData.getName()
        );
    }
}

package org.greekleanersinc.servicetemplate.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.greekleanersinc.servicetemplate.ServiceData;
import org.greekleanersinc.servicetemplate.Request;
import org.greekleanersinc.servicetemplate.Response;
import org.greekleanersinc.servicetemplate.TemplateServiceGrpc;
import org.greekleanersinc.servicetemplate.model.TemplateData;

@GrpcService
@Slf4j
public class TemplateServiceImpl extends TemplateServiceGrpc.TemplateServiceImplBase {

    @Override
    public void findTemplate(Request request,
                             StreamObserver<Response> responseObserver) {
        Long id = request.getId();
        log.info("Finding template by id: {}", id);

        //var template = templateRepository.findById(id);
        Response response = Response
                .newBuilder()
                .setResponseData(convertToProto(new TemplateData()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public static ServiceData convertToProto(TemplateData template) {
        return ServiceData.newBuilder()
                .setId(template.getId())
                .setText(template.getText())
                .build();
    }

    public static TemplateData convertToPojo(TemplateData templateData) {
        return new TemplateData(
                templateData.getId(),
                templateData.getText()
        );
    }
}

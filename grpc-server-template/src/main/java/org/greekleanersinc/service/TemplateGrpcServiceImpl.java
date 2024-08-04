package org.greekleanersinc.service;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.greekleanersinc.servicetemplate.*;
import org.greekleanersinc.servicetemplate.model.TemplateData;
import org.greekleanersinc.servicetemplate.service.TemplateService;

@GrpcService
@Slf4j
public class TemplateGrpcServiceImpl extends TemplateServiceGrpc.TemplateServiceImplBase {
    private final TemplateService templateService;

    public TemplateGrpcServiceImpl(TemplateService templateService) {
        this.templateService = templateService;
    }

    @Override
    public void findTemplate(RequestById request,
                             StreamObserver<Response> responseObserver) {
        Long id = request.getId();
        log.info("Finding template by id: {}", id);

        try {
            var template = templateService.findTemplateById(id);
            Response response = Response.newBuilder()
                    .setResponseData(convertToProto(template))
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        }

    }

    @Override
    public void findAll(Empty request,
                        StreamObserver<Response> responseObserver) {
    }

    @Override
    public void createTemplate(RequestCreate request,
                               StreamObserver<Response> responseObserver) {
    }

    @Override
    public void updateTemplate(RequestCreate request,
                               StreamObserver<Response> responseObserver) {
    }

    @Override
    public void delete(RequestById request,
                       StreamObserver<Empty> responseObserver) {
    }


    public static ServiceData convertToProto(TemplateData template) {
        return ServiceData.newBuilder()
                .setId(template.getId())
                .setText(template.getText())
                .build();
    }

    public static TemplateData convertToPojo(ServiceData templateData) {
        return new TemplateData(
                templateData.getId(),
                templateData.getText()
        );
    }
}

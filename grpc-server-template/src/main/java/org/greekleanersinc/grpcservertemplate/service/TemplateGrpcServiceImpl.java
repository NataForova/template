package org.greekleanersinc.grpcservertemplate.service;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.greekleanersinc.model.BaseTemplateData;
import org.greekleanersinc.servicetemplate.*;
import org.greekleanersinc.servicetemplate.model.TemplateData;
import org.greekleanersinc.servicetemplate.service.TemplateService;

import java.util.ArrayList;
import java.util.List;

import static org.greekleanersinc.TemplateConverter.convertToProto;

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
        try {
            var templates = templateService.findAllTemplates();
            for (BaseTemplateData data : templates) {
                responseObserver.onNext(Response.newBuilder()
                        .setResponseData(convertToProto((TemplateData)data)).build());
            }
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void createTemplate(RequestCreate request,
                               StreamObserver<Response> responseObserver) {

        var createdTemplate = templateService.createTemplate(
                new BaseTemplateData(
                        request.getId(),
                        request.getText()));
        responseObserver.onNext(Response.newBuilder().setResponseData(
                convertToProto(createdTemplate)).build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateTemplate(RequestCreate request,
                               StreamObserver<Response> responseObserver) {
        var createdTemplate = templateService.updateTemplate(
                new BaseTemplateData(
                        request.getId(),
                        request.getText()));
        responseObserver.onNext(Response.newBuilder().setResponseData(
                convertToProto(createdTemplate)).build());
        responseObserver.onCompleted();
    }

    @Override
    public void delete(RequestById request,
                       StreamObserver<Empty> responseObserver) {
        var id = request.getId();
        templateService.deleteById(id);
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }
}

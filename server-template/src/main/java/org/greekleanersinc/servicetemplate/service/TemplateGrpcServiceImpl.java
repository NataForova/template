package org.greekleanersinc.servicetemplate.service;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.greekleanersinc.servicetemplate.ServiceData;
import org.greekleanersinc.servicetemplate.Request;
import org.greekleanersinc.servicetemplate.Response;
import org.greekleanersinc.servicetemplate.TemplateServiceGrpc;
import org.greekleanersinc.servicetemplate.model.TemplateData;
import org.greekleanersinc.servicetemplate.repository.TemplateRepository;

@GrpcService
@Slf4j
public class TemplateGrpcServiceImpl extends TemplateServiceGrpc.TemplateServiceImplBase {
    private final TemplateRepository templateRepository;

    public TemplateGrpcServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public void findTemplate(Request request,
                             StreamObserver<Response> responseObserver) {
        Long id = request.getId();
        log.info("Finding template by id: {}", id);

        try {
            TemplateData template = templateRepository.findById(id);
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

package org.greekleanersinc.clienttemplate.client;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.greekleanersinc.communication.GrpcCommunicationInterface;
import org.greekleanersinc.exception.ResourceNotFoundException;
import org.greekleanersinc.model.BaseTemplateData;
import org.greekleanersinc.servicetemplate.RequestById;
import org.greekleanersinc.servicetemplate.Response;
import org.greekleanersinc.servicetemplate.ServiceData;
import org.greekleanersinc.servicetemplate.TemplateServiceGrpc;
import org.greekleanersinc.servicetemplate.model.TemplateData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateGrpcClient implements GrpcCommunicationInterface {
    @GrpcClient("grpc-server")
    private TemplateServiceGrpc.TemplateServiceBlockingStub templateServiceStub;

    @Override
    public BaseTemplateData findTemplateById(Long id) throws ResourceNotFoundException {
        RequestById request = RequestById.newBuilder().setId(id)
                .build();
        var response = this.templateServiceStub.findTemplate(request);
        return convertToPojo(response.getResponseData());
    }

    @Override
    public List<BaseTemplateData> findAllTemplates() {
        return List.of();
    }

    @Override
    public BaseTemplateData createTemplate(BaseTemplateData baseTemplateData) {
        return null;
    }

    @Override
    public BaseTemplateData updateTemplate(BaseTemplateData baseTemplateData) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {

    }

    //TODO move convertors to grpc module
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

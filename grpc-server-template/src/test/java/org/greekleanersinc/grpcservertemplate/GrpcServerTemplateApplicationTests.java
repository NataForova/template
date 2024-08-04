package org.greekleanersinc.grpcservertemplate;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.greekleanersinc.servicetemplate.RequestById;
import org.greekleanersinc.servicetemplate.RequestCreate;
import org.greekleanersinc.servicetemplate.Response;
import org.greekleanersinc.servicetemplate.TemplateServiceGrpc;
import org.greekleanersinc.servicetemplate.model.TemplateData;
import org.greekleanersinc.servicetemplate.repository.TemplateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.greekleanersinc.TemplateConverter.convertToPojo;
import static org.junit.jupiter.api.Assertions.fail;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest
class GrpcServerTemplateApplicationTests {

    @Container
    @ServiceConnection
    static  PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Autowired
    private TemplateRepository repository;
    @GrpcClient("grpc-server")
    private TemplateServiceGrpc.TemplateServiceBlockingStub templateServiceStub;

    @Test
    void getTemplateDataTestPositive() {
        long templateId = 1L;
        var expectedResponse = new TemplateData(templateId, "test");
        RequestById request = RequestById.newBuilder().setId(templateId).build();
        Response response = templateServiceStub.findTemplate(request);
        assertThat(response).isNotNull();
        var actualResponse = convertToPojo(response.getResponseData());
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void getTemplateDataTestWhenIdNotFound() {
        long templateId = 3L;
        RequestById request = RequestById.newBuilder().setId(templateId).build();
        try {
            var response = templateServiceStub.findTemplate(request);
            fail();
        } catch (StatusRuntimeException e) {
            assertThat(e.getStatus().getCode()).isEqualTo(Status.Code.NOT_FOUND);
            assertThat(e.getMessage()).isEqualTo(String.format("NOT_FOUND: Template with id '%s' not found", templateId));
        }
    }

    @Test
    void createTemplateDataTestPositive() {
        long templateId = 99L;
        String text = "test 99";
        var expectedResponse = new TemplateData(templateId, text);
        RequestCreate request = RequestCreate.newBuilder().setId(templateId).setText(text).build();
        var response = templateServiceStub.createTemplate(request);
        assertThat(response).isNotNull();
        var actualResponse = convertToPojo(response.getResponseData());
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void updateTemplateDataTestPositive() {
        long templateId = 2L;
        String text = "test updated";
        var expectedResponse = new TemplateData(templateId, text);
        RequestCreate request = RequestCreate.newBuilder().setId(templateId).setText(text).build();
        var response = templateServiceStub.updateTemplate(request);
        assertThat(response).isNotNull();
        var actualResponse = convertToPojo(response.getResponseData());
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void deleteTemplateDataTestPositive() {
        long templateId = 1L;
        RequestById request = RequestById.newBuilder().setId(templateId).build();
        var response = templateServiceStub.delete(request);

        RequestById requestDeleted = RequestById.newBuilder().setId(templateId).build();
        try {
            var responseDeleted = templateServiceStub.findTemplate(requestDeleted);
            fail();
        } catch (StatusRuntimeException e) {
            assertThat(e.getStatus().getCode()).isEqualTo(Status.Code.NOT_FOUND);
            assertThat(e.getMessage()).isEqualTo(String.format("NOT_FOUND: Template with id '%s' not found", templateId));
        }
    }
}

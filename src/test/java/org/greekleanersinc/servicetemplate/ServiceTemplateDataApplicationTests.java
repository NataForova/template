package org.greekleanersinc.servicetemplate;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
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
import static org.greekleanersinc.servicetemplate.service.TemplateGrpcServiceImpl.convertToPojo;
import static org.junit.jupiter.api.Assertions.fail;

@Testcontainers
@SpringBootTest
class ServiceTemplateDataApplicationTests {

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
        Request request = Request.newBuilder().setId(templateId).build();
        Response response = templateServiceStub.findTemplate(request);
        assertThat(response).isNotNull();
        var actualResponse = convertToPojo(response.getResponseData());
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void getTemplateDataTestWhenIdNotFound() {
        long templateId = 2L;
        Request request = Request.newBuilder().setId(templateId).build();
        try {
            var response = templateServiceStub.findTemplate(request);
            fail();
        } catch (StatusRuntimeException e) {
            assertThat(e.getStatus().getCode()).isEqualTo(Status.Code.NOT_FOUND);
            assertThat(e.getMessage()).isEqualTo(String.format("NOT_FOUND: Template with id '%s' not found", templateId));

        }
    }
}

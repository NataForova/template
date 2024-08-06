package org.greekleanersinc.grpcserver;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.greekleanersinc.baseservice.model.Example;
import org.greekleanersinc.baseservice.repository.DataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.greekleanersinc.grpcserver.service.DataConverter.convertToPojo;
import static org.junit.jupiter.api.Assertions.fail;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest
class GrpcServerApplicationTests {

    @Container
    @ServiceConnection
    static  PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Autowired
    private DataRepository repository;
    @GrpcClient("grpc-server")
    private DataServiceGrpc.DataServiceBlockingStub templateServiceStub;

    @Test
    void getTemplateDataTestPositive() {
        long exampleId = 1L;
        var expectedResponse = new Example(exampleId, "test");
        RequestById request = RequestById.newBuilder().setId(exampleId).build();
        Response response = templateServiceStub.findData(request);
        assertThat(response).isNotNull();
        var actualResponse = convertToPojo(response.getResponseData());
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void getDataTestWhenIdNotFound() {
        long exampleId = 3L;
        RequestById request = RequestById.newBuilder().setId(exampleId).build();
        try {
            var response = templateServiceStub.findData(request);
            fail();
        } catch (StatusRuntimeException e) {
            assertThat(e.getStatus().getCode()).isEqualTo(Status.Code.NOT_FOUND);
            assertThat(e.getMessage()).isEqualTo(String.format("NOT_FOUND: Template with id '%s' not found", exampleId));
        }
    }

    @Test
    void createDataTestPositive() {
        long exampleId = 99L;
        String text = "test 99";
        var expectedResponse = new Example(exampleId, text);
        RequestCreate request = RequestCreate.newBuilder().setId(exampleId).setText(text).build();
        var response = templateServiceStub.createData(request);
        assertThat(response).isNotNull();
        var actualResponse = convertToPojo(response.getResponseData());
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void updateDataTestPositive() {
        long exampleId = 2L;
        String text = "test updated";
        var expectedResponse = new Example(exampleId, text);
        RequestCreate request = RequestCreate.newBuilder().setId(exampleId).setText(text).build();
        var response = templateServiceStub.updateData(request);
        assertThat(response).isNotNull();
        var actualResponse = convertToPojo(response.getResponseData());
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void deleteDataTestPositive() {
        long exampleId = 1L;
        RequestById request = RequestById.newBuilder().setId(exampleId).build();
        var response = templateServiceStub.delete(request);

        RequestById requestDeleted = RequestById.newBuilder().setId(exampleId).build();
        try {
            var responseDeleted = templateServiceStub.findData(requestDeleted);
            fail();
        } catch (StatusRuntimeException e) {
            assertThat(e.getStatus().getCode()).isEqualTo(Status.Code.NOT_FOUND);
            assertThat(e.getMessage()).isEqualTo(String.format("NOT_FOUND: Template with id '%s' not found", exampleId));
        }
    }
}

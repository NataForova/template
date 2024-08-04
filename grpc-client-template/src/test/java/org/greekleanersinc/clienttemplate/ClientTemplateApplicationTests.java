package org.greekleanersinc.clienttemplate;


import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.greekleanersinc.clienttemplate.client.TemplateGrpcClient;
import org.greekleanersinc.servicetemplate.Response;
import org.greekleanersinc.servicetemplate.TemplateServiceGrpc;
import org.greekleanersinc.servicetemplate.model.TemplateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.greekleanersinc.TemplateConverter.convertToProto;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientTemplateApplicationTests {

	@Mock
	private TemplateServiceGrpc.TemplateServiceBlockingStub templateServiceStub;

	@InjectMocks
	private TemplateGrpcClient templateGrpcClient;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void templateGrpcClientTestPositive() {
		Response mockResponse = Response.newBuilder()
				.setResponseData(convertToProto(new TemplateData(1L, "test")))
				.build();

		when(templateServiceStub.findTemplate(argThat(request -> request.getId() == 1L))).thenReturn(mockResponse);

		long templateId = 1L;
		var expectedResponse = new TemplateData(templateId, "test");
		var actualResponse = templateGrpcClient.findTemplateById(templateId);
		assertThat(actualResponse).isNotNull();
		assertThat(actualResponse).isEqualTo(expectedResponse);
	}

	@Test
	void templateGrpcClientWhenIdIsNotExisted() {
		long templateId = 2L;
		when(templateServiceStub.findTemplate(argThat(request -> request.getId() == 2L)))
				.thenThrow(new StatusRuntimeException(Status.NOT_FOUND));
		try {
			var response = templateGrpcClient.findTemplateById(templateId);
			fail();
		} catch (StatusRuntimeException e) {
			assertThat(e.getStatus().getCode()).isEqualTo(Status.Code.NOT_FOUND);
			assertThat(e.getMessage()).isEqualTo("NOT_FOUND");
		}
	}

}

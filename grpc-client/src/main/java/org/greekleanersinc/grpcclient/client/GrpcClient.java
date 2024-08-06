package org.greekleanersinc.grpcclient.client;

import org.greekleanersinc.communication.GrpcCommunicationInterface;
import org.greekleanersinc.exception.ResourceNotFoundException;
import org.greekleanersinc.grpcserver.DataServiceGrpc;
import org.greekleanersinc.grpcserver.RequestById;
import org.greekleanersinc.model.BaseData;

import org.springframework.stereotype.Service;

import java.util.List;

import static org.greekleanersinc.grpcserver.service.DataConverter.convertToPojo;

@Service
public class GrpcClient implements GrpcCommunicationInterface {
    @net.devh.boot.grpc.client.inject.GrpcClient("grpc-server")
    private DataServiceGrpc.DataServiceBlockingStub templateServiceStub;

    @Override
    public BaseData findDataById(Long id) throws ResourceNotFoundException {
        RequestById request = RequestById.newBuilder().setId(id)
                .build();
        var response = this.templateServiceStub.findData(request);
        return convertToPojo(response.getResponseData());
    }

    @Override
    public List<BaseData> findAllData() {
        return List.of();
    }

    @Override
    public BaseData createData(BaseData baseData) {
        return null;
    }

    @Override
    public BaseData updateData(BaseData baseData) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {

    }
}

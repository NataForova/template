package org.greekleanersinc.communication;

import org.greekleanersinc.exception.ResourceNotFoundException;
import org.greekleanersinc.model.BaseData;

import java.util.List;

public interface GrpcCommunicationInterface {

    BaseData findDataById(Long id) throws ResourceNotFoundException;
    List<BaseData> findAllData();
    BaseData createData(BaseData baseData);
    BaseData updateData(BaseData baseData) throws ResourceNotFoundException;
    void deleteById(Long id) throws ResourceNotFoundException;

}

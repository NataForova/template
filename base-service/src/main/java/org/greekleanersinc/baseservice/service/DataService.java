package org.greekleanersinc.baseservice.service;

import org.greekleanersinc.communication.GrpcCommunicationInterface;
import org.greekleanersinc.exception.ResourceNotFoundException;
import org.greekleanersinc.model.BaseData;
import org.greekleanersinc.baseservice.model.Example;
import org.greekleanersinc.baseservice.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService implements GrpcCommunicationInterface {
    private final DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public Example findDataById(Long id) throws ResourceNotFoundException {
        return dataRepository.findById(id);
    }

    @Override
    public List<BaseData> findAllData() {
        return new ArrayList<>(dataRepository.findAll());
    }

    @Override
    public Example createData(BaseData baseData) {
        return dataRepository.save(new Example(baseData.getId(), baseData.getText()));
    }

    @Override
    public Example updateData(BaseData baseData) throws ResourceNotFoundException {
        return dataRepository.update(new Example(baseData.getId(), baseData.getText()));
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        dataRepository.deleteById(id);
    }
}

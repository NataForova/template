package org.greekleanersinc.servicetemplate.service;

import org.greekleanersinc.communication.GrpcCommunicationInterface;
import org.greekleanersinc.exception.ResourceNotFoundException;
import org.greekleanersinc.model.BaseTemplateData;
import org.greekleanersinc.servicetemplate.model.TemplateData;
import org.greekleanersinc.servicetemplate.repository.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateService implements GrpcCommunicationInterface {
    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public TemplateData findTemplateById(Long id) throws ResourceNotFoundException {
        return templateRepository.findById(id);
    }

    @Override
    public List<BaseTemplateData> findAllTemplates() {
        return new ArrayList<>(templateRepository.findAll());
    }

    @Override
    public TemplateData createTemplate(BaseTemplateData baseTemplateData) {
        return templateRepository.save(new TemplateData(baseTemplateData.getId(), baseTemplateData.getText()));
    }

    @Override
    public TemplateData updateTemplate(BaseTemplateData baseTemplateData) throws ResourceNotFoundException {
        return templateRepository.update(new TemplateData(baseTemplateData.getId(), baseTemplateData.getText()));
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        templateRepository.deleteById(id);
    }
}

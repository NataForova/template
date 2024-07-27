package org.greekleanersinc.servicetemplate.service;

import org.greekleanersinc.servicetemplate.model.TemplateData;
import org.greekleanersinc.servicetemplate.repository.TemplateRepository;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {
    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }
    public TemplateData findTemplateById(long id) {
        return templateRepository.findById(id);
    }
}

package org.greekleanersinc.communication;

import org.greekleanersinc.exception.ResourceNotFoundException;
import org.greekleanersinc.model.BaseTemplateData;

import java.util.List;

public interface GrpcCommunicationInterface {

    BaseTemplateData findTemplateById(Long id) throws ResourceNotFoundException;
    List<BaseTemplateData> findAllTemplates();
    BaseTemplateData createTemplate(BaseTemplateData baseTemplateData);
    BaseTemplateData updateTemplate(BaseTemplateData baseTemplateData) throws ResourceNotFoundException;
    void deleteById(Long id) throws ResourceNotFoundException;

}

package org.greekleanersinc.servicetemplate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.greekleanersinc.exception.ResourceNotFoundException;
import org.greekleanersinc.servicetemplate.model.TemplateData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TemplateRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public TemplateData findById(Long id) {
        TemplateData templateData = entityManager.find(TemplateData.class, id);
        if (templateData == null) {
            throw new ResourceNotFoundException(String.format("Template with id '%s' not found", id));
        }
        return templateData;
    }

    public List<TemplateData> findAll() {
        List<TemplateData> templateDataList = entityManager.createQuery("SELECT t FROM TemplateData t", TemplateData.class).getResultList();
        if (templateDataList  == null) {
            throw new ResourceNotFoundException("Templates not found");
        }
        return templateDataList;
    }

    public TemplateData save(TemplateData template) {
        return entityManager.merge(template);
    }

    public void deleteById(Long id) {
        TemplateData template = findById(id);
        delete(template);
    }

    public void delete(TemplateData template) {
        entityManager.remove(template);
    }

    public TemplateData update(TemplateData template) {
        return entityManager.merge(template);
    }
}

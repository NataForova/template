package org.greekleanersinc.servicetemplate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.greekleanersinc.servicetemplate.exception.ResourceNotFoundException;
import org.greekleanersinc.servicetemplate.model.TemplateData;
import org.springframework.stereotype.Repository;

@Repository
public class TemplateRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public TemplateData findById(Long id) {
        var templateData = entityManager.find(TemplateData.class, id);
        if (templateData == null) {
            throw new ResourceNotFoundException(String.format("Template with id '%s' not found", id));
        }
        return templateData;
    }

    public void save(TemplateRepository template) {
        entityManager.persist(template);
    }

    public void deleteById(Long id) {
        TemplateData template = findById(id);
        delete(template);
    }

    public void delete(TemplateData template) {
        entityManager.remove(template);
    }

    public void update(TemplateData template) {
        entityManager.merge(template);
    }
}

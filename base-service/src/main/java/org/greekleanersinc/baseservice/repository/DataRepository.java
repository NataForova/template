package org.greekleanersinc.baseservice.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.greekleanersinc.exception.ResourceNotFoundException;
import org.greekleanersinc.baseservice.model.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Example findById(Long id) {
        Example templateExample = entityManager.find(Example.class, id);
        if (templateExample == null) {
            throw new ResourceNotFoundException(String.format("Example with id '%s' not found", id));
        }
        return templateExample;
    }

    public List<Example> findAll() {
        List<Example> templateExampleList = entityManager.createQuery("SELECT t FROM Example t", Example.class).getResultList();
        if (templateExampleList == null) {
            throw new ResourceNotFoundException("Example not found");
        }
        return templateExampleList;
    }

    @Transactional
    public Example save(Example template) {
        return entityManager.merge(template);
    }

    @Transactional
    public void deleteById(Long id) {
        Example template = findById(id);
        delete(template);
    }

    @Transactional
    public void delete(Example template) {
        entityManager.remove(template);
    }

    @Transactional
    public Example update(Example template) {
        return entityManager.merge(template);
    }
}

package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationHistoryRepository extends HibernateRepositoryBase implements AbstractRepository<EducationHistory> {
    @Override
    public EducationHistory findOne(Long id) {
        return getHibernateTemplate().get(EducationHistory.class, id);
    }

    @Override
    public EducationHistory findOne(String name) {
        return null;
    }

    @Override
    public List<EducationHistory> findAll() {
        return null;
    }

    @Override
    public void create(EducationHistory entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public EducationHistory update(EducationHistory entity) {
        return null;
    }

    @Override
    public void delete(EducationHistory entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
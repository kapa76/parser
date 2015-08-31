package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationTypeResumeRepository extends HibernateRepositoryBase implements AbstractRepository<EducationTypeResume> {
    @Override
    public EducationTypeResume findOne(Long id) {
        return getHibernateTemplate().get(EducationTypeResume.class, id);
    }

    @Override
    public EducationTypeResume findOne(String name) {
        return null;
    }

    @Override
    public List<EducationTypeResume> findAll() {
        return null;
    }

    @Override
    public void create(EducationTypeResume entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public EducationTypeResume update(EducationTypeResume entity) {
        return null;
    }

    @Override
    public void delete(EducationTypeResume entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
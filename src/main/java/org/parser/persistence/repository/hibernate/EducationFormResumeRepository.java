package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationFormResumeRepository extends HibernateRepositoryBase implements AbstractRepository<EducationFormResume> {
    @Override
    public EducationFormResume findOne(Long id) {
        return getHibernateTemplate().get(EducationFormResume.class, id);
    }

    @Override
    public EducationFormResume findOne(String name) {
        return null;
    }

    @Override
    public List<EducationFormResume> findAll() {
        return null;
    }

    @Override
    public void create(EducationFormResume entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public EducationFormResume update(EducationFormResume entity) {
        return null;
    }

    @Override
    public void delete(EducationFormResume entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
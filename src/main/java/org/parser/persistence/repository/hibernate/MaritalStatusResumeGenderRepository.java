package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaritalStatusResumeGenderRepository extends HibernateRepositoryBase implements AbstractRepository<MaritalStatusResumeGender> {
    @Override
    public MaritalStatusResumeGender findOne(Long id) {
        return getHibernateTemplate().get(MaritalStatusResumeGender.class, id);
    }

    @Override
    public MaritalStatusResumeGender findOne(String name) {
        return null;
    }

    @Override
    public List<MaritalStatusResumeGender> findAll() {
        return null;
    }

    @Override
    public void create(MaritalStatusResumeGender entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public MaritalStatusResumeGender update(MaritalStatusResumeGender entity) {
        return null;
    }

    @Override
    public void delete(MaritalStatusResumeGender entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageResumeRepository extends HibernateRepositoryBase implements AbstractRepository<LanguageResume> {
    @Override
    public LanguageResume findOne(Long id) {
        return getHibernateTemplate().get(LanguageResume.class, id);
    }

    @Override
    public LanguageResume findOne(String name) {
        return null;
    }

    @Override
    public List<LanguageResume> findAll() {
        return null;
    }

    @Override
    public void create(LanguageResume entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public LanguageResume update(LanguageResume entity) {
        return null;
    }

    @Override
    public void delete(LanguageResume entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
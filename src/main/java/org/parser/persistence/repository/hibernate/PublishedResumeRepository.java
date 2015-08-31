package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublishedResumeRepository extends HibernateRepositoryBase implements AbstractRepository<PublishedResume> {
    @Override
    public PublishedResume findOne(Long id) {
        return getHibernateTemplate().get(PublishedResume.class, id);
    }

    @Override
    public PublishedResume findOne(String name) {
        return null;
    }

    @Override
    public List<PublishedResume> findAll() {
        return null;
    }

    @Override
    public void create(PublishedResume entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public PublishedResume update(PublishedResume entity) {
        return null;
    }

    @Override
    public void delete(PublishedResume entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
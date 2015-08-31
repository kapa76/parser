package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SocialLinksResumeRepository extends HibernateRepositoryBase implements AbstractRepository<SocialLinksResume> {
    @Override
    public SocialLinksResume findOne(Long id) {
        return getHibernateTemplate().get(SocialLinksResume.class, id);
    }

    @Override
    public SocialLinksResume findOne(String name) {
        return null;
    }

    @Override
    public List<SocialLinksResume> findAll() {
        return null;
    }

    @Override
    public void create(SocialLinksResume entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public SocialLinksResume update(SocialLinksResume entity) {
        return null;
    }

    @Override
    public void delete(SocialLinksResume entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
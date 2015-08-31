package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SiteRepository extends HibernateRepositoryBase implements AbstractRepository<Site> {
    @Override
    public Site findOne(Long id) {
        return getHibernateTemplate().get(Site.class, id);
    }

    @Override
    public Site findOne(String name) {
        return null;
    }

    @Override
    public List<Site> findAll() {
        return null;
    }

    @Override
    public void create(Site entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Site update(Site entity) {
        return null;
    }

    @Override
    public void delete(Site entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SiteRepository extends AbstractRepo {

    public Site findOne(Long id) {
        return (Site) sessionFactory.openSession().get(Site.class, id);
    }


    public Site findOne(String name) {
        return null;
    }


    public List<Site> findAll() {
        return null;
    }


    public void create(Site entity) {
        sessionFactory.openSession().save(entity);
    }


    public Site update(Site entity) {
        return null;
    }


    public void delete(Site entity) {

    }


    public void deleteById(long entityId) {

    }
}
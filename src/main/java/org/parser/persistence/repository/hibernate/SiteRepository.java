package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SiteRepository extends AbstractRepo {

    public Site findOne(Long id) {
        return (Site) sessionFactory.getCurrentSession().get(Site.class, id);
    }


    public Site findOne(String name) {
        return null;
    }


    public List<Site> findAll() {
        return null;
    }


    public void create(Site entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public Site update(Site entity) {
        return null;
    }


    public void delete(Site entity) {

    }


    public void deleteById(long entityId) {

    }
}
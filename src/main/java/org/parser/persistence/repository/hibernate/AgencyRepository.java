package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.Agency;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AgencyRepository extends AbstractRepo {

    public Agency findOne(Long id) {
        return (Agency) sessionFactory.openSession().get(Agency.class, id) ;
    }


    public Agency findOne(String name) {
        return (Agency)sessionFactory.openSession().createQuery("from agency p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<Agency> findAll() {
        return null;
    }


    public void create(Agency entity) {
        sessionFactory.openSession().save(entity);
    }


    public Agency update(Agency entity) {
        return null;
    }


    public void delete(Agency entity) {

    }


    public void deleteById(long entityId) {

    }
}
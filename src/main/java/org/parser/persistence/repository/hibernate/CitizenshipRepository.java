package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CitizenshipRepository extends AbstractRepo {

    public Citizenship findOne(Long id) {
        return (Citizenship) sessionFactory.openSession().get(Citizenship.class, id);
    }


    public Citizenship findOne(String name) {
        return (Citizenship)sessionFactory.openSession().createQuery("from citizenship p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<Citizenship> findAll() {
        return null;
    }


    public void create(Citizenship entity) {
        sessionFactory.openSession().save(entity);
    }


    public Citizenship update(Citizenship entity) {
        return null;
    }


    public void delete(Citizenship entity) {

    }


    public void deleteById(long entityId) {

    }
}
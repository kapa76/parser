package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyRepository extends AbstractRepo {

    public Currency findOne(Long id) {
        return (Currency) sessionFactory.openSession().get(Currency.class, id);
    }


    public Currency findOne(String name) {
        return (Currency)sessionFactory.openSession().createQuery("from currency p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<Currency> findAll() {
        return null;
    }


    public void create(Currency entity) {
        sessionFactory.openSession().save(entity);
    }


    public Currency update(Currency entity) {
        return null;
    }


    public void delete(Currency entity) {

    }

    public void deleteById(long entityId) {

    }
}
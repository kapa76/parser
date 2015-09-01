package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenderRepository extends AbstractRepo {

    public Gender findOne(Long id) {
        return (Gender) sessionFactory.openSession().get(Gender.class, id);
    }


    public Gender findOne(String name) {
        return null;
    }


    public List<Gender> findAll() {
        return null;
    }


    public void create(Gender entity) {
        sessionFactory.openSession().save(entity);
    }

    public Gender update(Gender entity) {
        return null;
    }


    public void delete(Gender entity) {

    }


    public void deleteById(long entityId) {

    }
}
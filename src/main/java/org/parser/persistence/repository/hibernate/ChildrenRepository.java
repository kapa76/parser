package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.Children;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChildrenRepository extends AbstractRepo {

    public Children findOne(Long id) {
        return (Children) sessionFactory.openSession().get(Children.class, id);
    }


    public Children findOne(String name) {
        return (Children)sessionFactory.openSession().createQuery("from children p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<Children> findAll() {
        return null;
    }


    public void create(Children entity) {
        sessionFactory.openSession().save(entity);
    }


    public Children update(Children entity) {
        return null;
    }


    public void delete(Children entity) {

    }


    public void deleteById(long entityId) {

    }
}
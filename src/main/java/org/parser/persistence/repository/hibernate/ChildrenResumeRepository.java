package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChildrenResumeRepository extends AbstractRepo {

    public ChildrenResume findOne(Long id) {
        return (ChildrenResume) sessionFactory.openSession().get(ChildrenResume.class, id);
    }


    public ChildrenResume findOne(String name) {
        return (ChildrenResume)sessionFactory.openSession().createQuery("from children_resume p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<ChildrenResume> findAll() {
        return null;
    }


    public void create(ChildrenResume entity) {
        sessionFactory.openSession().save(entity);
    }

    public ChildrenResume update(ChildrenResume entity) {
        return null;
    }


    public void delete(ChildrenResume entity) {

    }


    public void deleteById(long entityId) {

    }
}
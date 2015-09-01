package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChildrenResumeRepository extends AbstractRepo {

    public ChildrenResume findOne(Long id) {
        return (ChildrenResume) sessionFactory.getCurrentSession().get(ChildrenResume.class, id);
    }


    public ChildrenResume findOne(String name) {
        return (ChildrenResume)sessionFactory.getCurrentSession().createQuery("from children_resume p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<ChildrenResume> findAll() {
        return null;
    }


    public void create(ChildrenResume entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }

    public ChildrenResume update(ChildrenResume entity) {
        return null;
    }


    public void delete(ChildrenResume entity) {

    }


    public void deleteById(long entityId) {

    }
}
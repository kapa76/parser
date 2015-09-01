package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkTypeRepository extends AbstractRepo {

    public WorkType findOne(Long id) {
        return (WorkType) sessionFactory.getCurrentSession().get(WorkType.class, id);
    }


    public WorkType findOne(String name) {
        return null;
    }


    public List<WorkType> findAll() {
        return null;
    }


    public void create(WorkType entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public WorkType update(WorkType entity) {
        return null;
    }


    public void delete(WorkType entity) {

    }


    public void deleteById(long entityId) {

    }
}
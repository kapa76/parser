package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeOfWorkRepository extends AbstractRepo {

    public TypeOfWork findOne(Long id) {
        return (TypeOfWork) sessionFactory.getCurrentSession().get(TypeOfWork.class, id);
    }


    public TypeOfWork findOne(String name) {
        return null;
    }


    public List<TypeOfWork> findAll() {
        return null;
    }


    public void create(TypeOfWork entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public TypeOfWork update(TypeOfWork entity) {
        return null;
    }


    public void delete(TypeOfWork entity) {

    }


    public void deleteById(long entityId) {

    }
}
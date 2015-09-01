package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeOfWorkRepository extends AbstractRepo {

    public TypeOfWork findOne(Long id) {
        return (TypeOfWork) sessionFactory.openSession().get(TypeOfWork.class, id);
    }


    public TypeOfWork findOne(String name) {
        return null;
    }


    public List<TypeOfWork> findAll() {
        return null;
    }


    public void create(TypeOfWork entity) {
        sessionFactory.openSession().save(entity);
    }


    public TypeOfWork update(TypeOfWork entity) {
        return null;
    }


    public void delete(TypeOfWork entity) {

    }


    public void deleteById(long entityId) {

    }
}
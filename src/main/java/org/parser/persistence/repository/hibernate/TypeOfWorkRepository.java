package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeOfWorkRepository extends HibernateRepositoryBase implements AbstractRepository<TypeOfWork> {
    @Override
    public TypeOfWork findOne(Long id) {
        return getHibernateTemplate().get(TypeOfWork.class, id);
    }

    @Override
    public TypeOfWork findOne(String name) {
        return null;
    }

    @Override
    public List<TypeOfWork> findAll() {
        return null;
    }

    @Override
    public void create(TypeOfWork entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public TypeOfWork update(TypeOfWork entity) {
        return null;
    }

    @Override
    public void delete(TypeOfWork entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
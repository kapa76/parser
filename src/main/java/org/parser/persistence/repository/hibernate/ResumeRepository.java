package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResumeRepository extends HibernateRepositoryBase implements AbstractRepository<Resume> {
    @Override
    public Resume findOne(Long id) {
        return getHibernateTemplate().get(Resume.class, id);
    }

    @Override
    public Resume findOne(String name) {
        return null;
    }

    @Override
    public List<Resume> findAll() {
        return null;
    }

    @Override
    public void create(Resume entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Resume update(Resume entity) {
        return null;
    }

    @Override
    public void delete(Resume entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResumeRepository extends AbstractRepo {

    public Resume findOne(Long id) {
        return (Resume) sessionFactory.openSession().get(Resume.class, id);
    }


    public Resume findOne(String name) {
        return null;
    }


    public List<Resume> findAll() {
        return null;
    }


    public void create(Resume entity) {
        sessionFactory.openSession().save(entity);
    }


    public Resume update(Resume entity) {
        return null;
    }


    public void delete(Resume entity) {

    }


    public void deleteById(long entityId) {

    }
}
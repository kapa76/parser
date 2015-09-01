package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Resume;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResumeRepository extends AbstractRepo {

    public Resume findOne(Long id) {
        return (Resume) sessionFactory.getCurrentSession().get(Resume.class, id);
    }


    public Resume findOne(String name) {
        return null;
    }


    public List<Resume> findAll() {
        return null;
    }


    public void create(Resume entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public Resume update(Resume entity) {
        return null;
    }


    public void delete(Resume entity) {

    }


    public void deleteById(long entityId) {

    }
}
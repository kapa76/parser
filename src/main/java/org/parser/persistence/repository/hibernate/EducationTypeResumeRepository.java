package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.EducationTypeResume;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationTypeResumeRepository extends AbstractRepo {

    public EducationTypeResume findOne(Long id) {
        return (EducationTypeResume) sessionFactory.getCurrentSession().get(EducationTypeResume.class, id);
    }


    public EducationTypeResume findOne(String name) {
        return null;
    }


    public List<EducationTypeResume> findAll() {
        return null;
    }


    public void create(EducationTypeResume entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public EducationTypeResume update(EducationTypeResume entity) {
        return null;
    }


    public void delete(EducationTypeResume entity) {

    }

    public void deleteById(long entityId) {

    }

    public EducationTypeResume findOne(String name, long id) {
        return (EducationTypeResume) sessionFactory.getCurrentSession().createQuery("from education_type_resume p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}
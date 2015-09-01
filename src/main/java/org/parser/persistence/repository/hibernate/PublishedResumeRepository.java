package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublishedResumeRepository extends AbstractRepo {

    public PublishedResume findOne(Long id) {
        return (PublishedResume) sessionFactory.openSession().get(PublishedResume.class, id);
    }


    public PublishedResume findOne(String name) {
        return null;
    }


    public List<PublishedResume> findAll() {
        return null;
    }


    public void create(PublishedResume entity) {
        sessionFactory.openSession().save(entity);
    }


    public PublishedResume update(PublishedResume entity) {
        return null;
    }

    public void delete(PublishedResume entity) {

    }


    public void deleteById(long entityId) {

    }
}
package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageRepository extends AbstractRepo {

    public Language findOne(Long id) {
        return (Language) sessionFactory.openSession().get(Language.class, id);
    }


    public Language findOne(String name) {
        return null;
    }


    public List<Language> findAll() {
        return null;
    }


    public void create(Language entity) {
        sessionFactory.openSession().save(entity);
    }


    public Language update(Language entity) {
        return null;
    }


    public void delete(Language entity) {

    }


    public void deleteById(long entityId) {

    }
}
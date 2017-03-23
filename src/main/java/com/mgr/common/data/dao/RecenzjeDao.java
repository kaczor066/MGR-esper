package com.mgr.common.data.dao;

import com.mgr.common.data.RecenzjeEsper;
import com.mgr.common.data.RecenzjeEsperId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacja klasy posiadajacej metody dostepu do tabeli RecenzjeEsper
 *
 * @author michal
 */
@Repository
public class RecenzjeDao {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * Metoda wpisujaca recenzje do tabeli
     *
     * @param review
     */
    @Transactional
    public void insert(RecenzjeEsper review) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(review);
        session.getTransaction().commit();
    }

    /**
     * Metoda wyszukujaca recenzje po ID
     *
     * @param id
     * @param session
     * @return
     */
    public RecenzjeEsper findById(RecenzjeEsperId id, Session session) {
        return (RecenzjeEsper) session.get(RecenzjeEsper.class, id);

    }
}

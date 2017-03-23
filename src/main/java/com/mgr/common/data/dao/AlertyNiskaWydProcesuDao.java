package com.mgr.common.data.dao;

import com.mgr.common.data.AlertyNiskaWydProcesuEsper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacja klasy posiadajacej metody dostepu do tabeli
 * AlertyNiskaWydProcesuEsper
 *
 * @author michal
 */
@Repository
public class AlertyNiskaWydProcesuDao extends
        AlertTablesDao<AlertyNiskaWydProcesuEsper> {
    public AlertyNiskaWydProcesuDao() {
        super(AlertyNiskaWydProcesuEsper.class);
    }

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        // TODO Auto-generated method stub
        super.sessionFactory = sessionFactory;
    }

    /**
     * Metoda usuwajaca alert o wskazanym ID
     *
     * @param id
     */
    @Transactional
    public void deleteById(String id) {
        AlertyNiskaWydProcesuEsper alert = new AlertyNiskaWydProcesuEsper();
        alert.setSeria(id);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(alert);
        session.getTransaction().commit();

    }
}

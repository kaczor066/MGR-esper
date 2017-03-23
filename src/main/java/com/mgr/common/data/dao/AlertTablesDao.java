package com.mgr.common.data.dao;

import com.mgr.common.data.EsperAlertData;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Abstrakcyjna klasa zawierajacy metody dostepu do tabeli z alertami Jako
 * parametr przyjmuje klase opisujaca tabele alertow
 *
 * @param <T>
 * @author michal
 */
public abstract class AlertTablesDao<T extends EsperAlertData> {
    private Class type;

    public AlertTablesDao(Class _type) {
        type = _type;
    }

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    protected SessionFactory sessionFactory;

    public abstract void setSessionFactory(SessionFactory sessionFactory);

    /**
     * Metoda UPSERT
     *
     * @param alert
     */
    @Transactional
    public void insertOrUpdate(T alert) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        T tmp = findById(alert.getId(), session);
        if (tmp == null)
            session.save(alert);
        else {
            tmp.setPriority(tmp.getPriority().add(alert.getPriority()));
            session.update(tmp);
        }
        session.getTransaction().commit();
    }

    /**
     * Metoda select *
     *
     * @return
     */
    @Transactional
    public List<T> selectAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(type);
        List<T> alerts = (List<T>) criteria.list();
        session.getTransaction().commit();
        return alerts;
    }

    /**
     * Metoda wyszukujaca obiektu z danym ID
     *
     * @param id
     * @param session
     * @return
     */
    public T findById(Object id, Session session) {
        return (T) session.get(type, (Serializable) id);

    }

}

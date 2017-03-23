package com.mgr.common.data.dao;

import com.mgr.common.data.AlertyNiskaJakoscEsper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementacja klasy posiadajacej metody dostepu do tabeli
 * AlertyNiskaJakoscEsper
 *
 * @author michal
 */
@Repository
public class AlertyNiskaJakoscDao extends
        AlertTablesDao<AlertyNiskaJakoscEsper> {
    public AlertyNiskaJakoscDao() {
        super(AlertyNiskaJakoscEsper.class);
    }

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        // TODO Auto-generated method stub
        super.sessionFactory = sessionFactory;
    }

    /**
     * Metoda wybierajace grupe rekordow z tabeli na podstawie serii i poziomu
     *
     * @param seria
     * @param poziom
     * @return
     */
    @Transactional
    public List<AlertyNiskaJakoscEsper> selectGroup(String seria, int poziom) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session
                .createCriteria(AlertyNiskaJakoscEsper.class);
        criteria.add(Restrictions.eq("id.seria", seria));
        criteria.add(Restrictions.gt("id.poziom", new BigDecimal(poziom)));
        List<AlertyNiskaJakoscEsper> alerts = (List<AlertyNiskaJakoscEsper>) criteria
                .list();
        session.getTransaction().commit();
        return alerts;
    }
}

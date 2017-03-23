package com.mgr.common.data.dao;

import com.mgr.common.data.AlertyPomyloneDostawyEsper;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementacja klasy posiadajacej metody dostepu do tabeli
 * AlertyPomyloneDostawyEsper
 *
 * @author michal
 */
@Repository
public class AlertyPomyloneDostawyDao extends
        AlertTablesDao<AlertyPomyloneDostawyEsper> {
    public AlertyPomyloneDostawyDao() {
        super(AlertyPomyloneDostawyEsper.class);
    }

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        // TODO Auto-generated method stub
        super.sessionFactory = sessionFactory;
    }
}

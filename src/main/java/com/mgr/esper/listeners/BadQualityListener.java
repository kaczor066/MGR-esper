package com.mgr.esper.listeners;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.mgr.common.data.AlertyNiskaJakoscEsper;
import com.mgr.common.data.AlertyNiskaJakoscEsperId;
import com.mgr.common.data.dao.AlertyNiskaJakoscDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Klasa odpowiadajaca za logike wykrywania zlej jakosci
 *
 * @author michal
 */
@Component
public class BadQualityListener implements UpdateListener {
    private static final Logger LOG = LoggerFactory
            .getLogger(BadPackingListener.class);
    @Autowired
    AlertyNiskaJakoscDao alertTable;

    /**
     * Implementacja zalozonej logiki
     */
    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        String seria = newEvents[0].get("seria").toString();
        String dostawa_sklep = newEvents[0].get("dostawa_sklep").toString();
        String dostawa_klient = newEvents[0].get("dostawa_klient").toString();
        int poziom = 3;
        int priority = 4 - new Integer(newEvents[0].get("jakosc").toString());
        if (dostawa_klient == "null" && dostawa_sklep == "null")
            poziom = 1;
        else if (dostawa_klient == "null")
            poziom = 2;

        List<AlertyNiskaJakoscEsper> previousAlerts = alertTable.selectGroup(
                seria, poziom);
        if (previousAlerts.size() == 0)
            generateAlert(seria, poziom, priority);
        else {
            for (AlertyNiskaJakoscEsper alert : previousAlerts) {
                priority += 2 * alert.getPriority().intValue();
            }
            generateAlert(seria, poziom, priority);
        }

    }

    private void generateAlert(String seria, int level, int priority) {
        alertTable.insertOrUpdate(new AlertyNiskaJakoscEsper(
                new AlertyNiskaJakoscEsperId(seria, new BigDecimal(level)),
                new BigDecimal(priority), new Timestamp(System
                .currentTimeMillis())));
    }
}

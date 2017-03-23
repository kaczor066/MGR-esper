package com.mgr.esper.listeners;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.mgr.common.data.AlertyPomyloneDostawyEsper;
import com.mgr.common.data.dao.AlertyPomyloneDostawyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Klasa odpowiadajaca za logike wykrywania pomylek w dostawie
 *
 * @author michal
 */
@Component
public class MissedDeliveriesListener implements UpdateListener {
    private static final Logger LOG = LoggerFactory
            .getLogger(ReviewListener.class);
    @Autowired
    AlertyPomyloneDostawyDao alertTable;

    /**
     * Implementacja zalozonej logiki
     */
    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        LOG.error("Missed delivery detected!!!! ID_OCENIANEGO:{}",
                newEvents[0].get("id_ocenianego"));
        alertTable.insertOrUpdate(new AlertyPomyloneDostawyEsper(newEvents[0]
                .get("id_ocenianego").toString(), new BigDecimal(1),
                new Timestamp(System.currentTimeMillis())));
    }
}

package com.mgr.esper.listeners;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.mgr.common.data.AlertySlabePakowanieEsper;
import com.mgr.common.data.dao.AlertySlabePakowanieDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Klasa odpowiadajaca za logike wykrywania zlego pakowania
 *
 * @author michal
 */
@Component
public class BadPackingListener implements UpdateListener {
    private static final Logger LOG = LoggerFactory
            .getLogger(BadPackingListener.class);
    @Autowired
    AlertySlabePakowanieDao alertTable;

    /**
     * Implementacja zalozonej logiki
     */
    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        LOG.error("Bad Packing detected!!!! ID_OCENIANEGO:{}, AVG:{}",
                newEvents[0].get("id_ocenianego"),
                newEvents[0].get("avgPakowanie"));
        alertTable.insertOrUpdate(new AlertySlabePakowanieEsper(newEvents[0]
                .get("id_ocenianego").toString(), new BigDecimal(1),
                new Timestamp(System.currentTimeMillis()), new BigDecimal(
                newEvents[0].get("avgPakowanie").toString())));

    }
}

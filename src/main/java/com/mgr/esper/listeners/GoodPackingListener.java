package com.mgr.esper.listeners;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.mgr.common.data.dao.AlertySlabePakowanieDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Klasa odpowiadajaca za logike wykrywania poprawy w pakowaniu
 *
 * @author michal
 */
@Component
public class GoodPackingListener implements UpdateListener {
    private static final Logger LOG = LoggerFactory
            .getLogger(GoodPackingListener.class);
    @Autowired
    AlertySlabePakowanieDao alertTable;

    /**
     * Implementacja zalozonej logiki
     */
    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        LOG.error(
                "Improvement in packing detected!!!! ID_OCENIANEGO:{}, AVG:{}",
                newEvents[0].get("id_ocenianego"),
                newEvents[0].get("avgPakowanie"));
        alertTable.deleteById(newEvents[0].get("id_ocenianego").toString());

    }
}

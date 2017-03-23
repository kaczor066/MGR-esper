package com.mgr.esper.listeners;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.mgr.common.data.dao.AlertySlCzasDostawyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Klasa odpowiadajaca za logike wykrywania poprawy czasow dostawy
 *
 * @author michal
 */
@Component
public class GoodDeliveryTimeListener implements UpdateListener {
    private static final Logger LOG = LoggerFactory
            .getLogger(GoodDeliveryTimeListener.class);
    @Autowired
    AlertySlCzasDostawyDao alertTable;

    /**
     * Implementacja zalozonej logiki
     */
    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        LOG.error(
                "Improvement in delivery time detected!!!! ID_OCENIANEGO:{}, AVG:{}",
                newEvents[0].get("id_ocenianego"),
                newEvents[0].get("avgCzasDostawy"));
        alertTable.deleteById(newEvents[0].get("id_ocenianego").toString());

    }
}

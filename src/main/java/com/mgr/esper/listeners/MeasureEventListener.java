package com.mgr.esper.listeners;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.mgr.common.data.AlertyNiskaWydProcesuEsper;
import com.mgr.common.data.dao.AlertyNiskaWydProcesuDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Klasa odpowiadajaca za logike pomiarow wspolczynnnika wydajnosci procesu na
 * podstawie pomiarow
 *
 * @author michal
 */
@Component
public class MeasureEventListener implements UpdateListener {

    private static final Logger LOG = LoggerFactory
            .getLogger(MeasureEventListener.class);
    @Autowired
    AlertyNiskaWydProcesuDao alertTable;

    /**
     * Implementacja zalozonej logiki
     */
    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        print(newEvents[0]);
        generateAlert(newEvents[0]);

    }

    private void generateAlert(EventBean eventBean) {
        double CP = calculateCP(eventBean);
        if (CP < 1) {
            LOG.error("NISKA WYDAJNOSC PROCESU!!!! Seria:{}, CP:{}",
                    eventBean.get("measure.seria"), CP);
            alertTable.insertOrUpdate(new AlertyNiskaWydProcesuEsper(eventBean
                    .get("measure.seria").toString(), new BigDecimal(1),
                    new Timestamp(System.currentTimeMillis())));
        } else {
            alertTable.deleteById(eventBean.get("measure.seria").toString());
        }
    }

    private void print(EventBean event) {
        LOG.debug(
                "stddev:{}, count:{}, avgWaga: {}, seria:{}, zakres_dolny:{}, zakres_gorny:{}",
                event.get("measure.stddevWaga"), event.get("measure.cnt"),
                event.get("measure.avgWaga"), event.get("measure.seria"),
                event.get("range.zakres_dolny"),
                event.get("range.zakres_gorny"));
    }

    private double calculateCP(EventBean event) {
        double divide = 3 * (double) event.get("measure.stddevWaga");
        if (divide == 0)
            return Double.MAX_VALUE;
        double CP1 = ((double) event.get("measure.avgWaga") - (double) event
                .get("range.zakres_dolny")) / divide;
        double CP2 = ((double) event.get("range.zakres_gorny") - (double) event
                .get("measure.avgWaga")) / divide;
        return Math.min(CP1, CP2);
    }
}

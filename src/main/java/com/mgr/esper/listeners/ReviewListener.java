package com.mgr.esper.listeners;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.mgr.common.data.RecenzjeEsper;
import com.mgr.common.data.RecenzjeEsperId;
import com.mgr.common.data.dao.RecenzjeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Klasa odpowiadajaca za zapisanie recenzji do tabeli
 *
 * @author michal
 */
@Component
public class ReviewListener implements UpdateListener {
    private static final Logger LOG = LoggerFactory
            .getLogger(ReviewListener.class);
    @Autowired
    RecenzjeDao reviewTable;

    /**
     * Implementacja zalozonej logiki
     */
    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        LOG.debug("Inserting new event to RecenzjeEsper");
        reviewTable.insert(mapToDbStruct(newEvents[0]));
    }

    private RecenzjeEsper mapToDbStruct(EventBean event) {
        RecenzjeEsperId id = new RecenzjeEsperId(event.get("seria").toString(),
                event.get("dostawa_sklep").toString(), event.get(
                "dostawa_klient").toString(), event.get(
                "id_oceniajacego").toString());

        RecenzjeEsper review = new RecenzjeEsper(id);

        review.setCzasDostawy(new BigDecimal(event.get("czas_dostawy")
                .toString()));
        review.setJakosc(new BigDecimal(event.get("jakosc").toString()));
        review.setPakowanie(new BigDecimal(event.get("pakowanie").toString()));
        review.setZgodnosc(new BigDecimal(event.get("zgodnosc").toString()));
        review.setDataRecenzji((Timestamp) event.get("data_recenzji"));
        review.setIdOcenianego(event.get("id_ocenianego").toString());

        return review;
    }
}

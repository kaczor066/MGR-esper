package com.mgr.common.esper;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.mgr.esper.events.MeasureEvent;
import com.mgr.esper.events.ReviewEvent;
import com.mgr.esper.events.SetRangeEvent;
import com.mgr.esper.listeners.*;
import com.mgr.esper.statements.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Glowna klasa opakowujaca silnik Esper
 *
 * @author michal
 */
@Component
public class EsperEngine {

    private static final String engineURI = "MGREsperEngine";
    private EPServiceProvider epService;

    private static final Logger LOG = LoggerFactory
            .getLogger(EsperEngine.class);

    public EPServiceProvider getEpService() {
        return epService;
    }

    @Autowired
    ApplicationContext context;

    /**
     * Metoda inicjujaca Uruchamiamy tutaj silnik, rejestrujemy w nim
     * odpowiednie eventy oraz statementy Do statementow dodajemy listenery
     */
    @PostConstruct
    public void init() {
        // Configure engine with event names to make the statements more
        // readable.
        // This could also be done in a configuration file.
        Configuration configuration = new Configuration();
        configuration.addEventType("MeasureEvent", MeasureEvent.class);
        configuration.addEventType("SetRangeEvent", SetRangeEvent.class);
        configuration.addEventType("ReviewEvent", ReviewEvent.class);

        // Get engine instance
        epService = EPServiceProviderManager.getProvider(engineURI,
                configuration);

        // Statement and its listener for calculating CP
        MeasureEventStatement measureEventStmt = new MeasureEventStatement(
                epService.getEPAdministrator());
        measureEventStmt.addListener(context
                .getBean(MeasureEventListener.class));

        // Saving review
        ReviewStatement reviewStatement = new ReviewStatement(
                epService.getEPAdministrator());
        reviewStatement.addListener(context.getBean(ReviewListener.class));

        // Detecting bad packing
        BadPackingStatement badPackingStatement = new BadPackingStatement(
                epService.getEPAdministrator());
        badPackingStatement.addListener(context
                .getBean(BadPackingListener.class));

        // Detecting that packing improves
        GoodPackingStatement goodPackingStatement = new GoodPackingStatement(
                epService.getEPAdministrator());
        goodPackingStatement.addListener(context
                .getBean(GoodPackingListener.class));

        // Detecting bad delivery time
        BadDeliveryTimeStatement badDeliveryTimeStatement = new BadDeliveryTimeStatement(
                epService.getEPAdministrator());
        badDeliveryTimeStatement.addListener(context
                .getBean(BadDeliveryTimeListener.class));

        // Detecting that delivery time improves
        GoodDeliveryTimeStatement goodDeliveryTimeStatement = new GoodDeliveryTimeStatement(
                epService.getEPAdministrator());
        goodDeliveryTimeStatement.addListener(context
                .getBean(GoodDeliveryTimeListener.class));

        // Detecting missed deliveries
        MissedDeliveriesStatement missedDeliveriesStatement = new MissedDeliveriesStatement(
                epService.getEPAdministrator());
        missedDeliveriesStatement.addListener(context
                .getBean(MissedDeliveriesListener.class));

        // Quality management
        BadQualityStatement badQualityStatement = new BadQualityStatement(
                epService.getEPAdministrator());
        badQualityStatement.addListener(context
                .getBean(BadQualityListener.class));

    }

    /**
     * Wsysylanie eventu do silnika
     *
     * @param event
     */
    public synchronized void sendEvent(Object event) {
        if (event == null) {
            LOG.error("Cannot send event that is null");
            return;
        }
        epService.getEPRuntime().sendEvent(event);
    }
}

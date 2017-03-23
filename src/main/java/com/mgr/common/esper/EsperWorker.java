package com.mgr.common.esper;

import com.mgr.common.queue.PassEventQueue;
import com.mgr.common.worker.Worker;
import com.mgr.esper.events.HashMapToEsperEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Klasa watku zbierajacego komunikaty z wewnetrznej kolejki programu
 *
 * @author michal
 */
@Component
@Scope("prototype")
public class EsperWorker extends Worker {

    @Autowired
    protected PassEventQueue queue;
    @Autowired
    protected EsperEngine engine;
    private HashMapToEsperEventMapper mapper = new HashMapToEsperEventMapper();

    /**
     * Glowna metoda zbierajaca komunikat z kolejki i wysylajaca go do silnika
     * Esper
     */
    @Override
    public void doWork() {
        HashMap<String, String> map = null;
        try {
            map = queue.take();
        } catch (InterruptedException e) {
            LOG.info("Error:", e);
        }
        if (map == null) {
            this.shutdown();
            return;
        } else {
            LOG.debug("Sending event to engine...");
            engine.sendEvent(mapper.map(map));
        }

    }

}

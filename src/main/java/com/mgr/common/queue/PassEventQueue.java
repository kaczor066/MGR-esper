package com.mgr.common.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Wewnetrzna kolejka do przekazywania komunikatow pomiedzy watkami
 * odbierajacymi, a tymi wysylajacymi do silnika Esper
 *
 * @author michal
 */
@Component
public class PassEventQueue {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    protected BlockingQueue<HashMap<String, String>> queue = new LinkedBlockingQueue<>();

    /**
     * Pobranie komunikatu
     *
     * @return
     * @throws InterruptedException
     */
    public synchronized HashMap<String, String> take()
            throws InterruptedException {
        HashMap<String, String> event = queue.take();
        LOG.debug("Event taken from Queue. Event:[{}]", printEvent(event));
        return event;
    }

    /**
     * Wrzucenie komunikatu
     *
     * @param event
     * @throws InterruptedException
     */
    public synchronized void put(HashMap<String, String> event)
            throws InterruptedException {
        LOG.debug("Event inserted to Queue. Event:[{}]", printEvent(event));
        queue.put(event);
    }

    public void refresh() {
        LOG.info("Refreshing. {} requests will not be processed.", queue.size());
        queue = new LinkedBlockingQueue<>();
    }

    private String printEvent(HashMap<String, String> event) {
        String ret = "";
        for (Entry<String, String> entry : event.entrySet())
            ret = ret + "{Key:" + entry.getKey() + " Value:" + entry.getValue()
                    + "} ";
        return ret;
    }

}

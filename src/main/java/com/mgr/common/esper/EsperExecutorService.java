package com.mgr.common.esper;

import com.mgr.common.queue.PassEventQueue;
import com.mgr.common.worker.AbstractExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Klasa uruchamiajaca watki wysylajace komunikaty do silnika esper
 *
 * @author michal
 */
@Component
public class EsperExecutorService extends AbstractExecutorService {

    @Autowired
    EsperWorkerFactory workerFactory;
    @Value("${esper.workers}")
    Integer workers;
    @Autowired
    protected PassEventQueue queue;

    @PostConstruct
    public void startUp() {
        super.workerFactory = workerFactory;
        super.init(workers);
    }

    /**
     * Metoda wysylajaca "zatrute" obiekty do watkow, by zakomunikowac im
     * zakonczenie dzialania programu
     */
    @Override
    protected void sendPoisonousRequests() {
        for (int i = 0; i < workers; i++) {
            try {
                queue.put(null);
            } catch (InterruptedException e) {
                i--;
            }
        }

    }

}

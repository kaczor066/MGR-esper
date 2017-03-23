package com.mgr.common.esper;

import com.mgr.common.worker.Worker;
import com.mgr.common.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Klasa pelniaca funkcje fabryki watkow wysylajacych komunikaty do silnika
 * Esper
 *
 * @author michal
 */
@Component
public class EsperWorkerFactory implements WorkerFactory {

    @Autowired
    ApplicationContext appContext;

    /**
     * Stworzenie nowego watku
     */
    @Override
    public Worker newWorker() {
        Worker worker = appContext.getBean(EsperWorker.class);
        return worker;
    }

    @Override
    public void close() {

    }

}

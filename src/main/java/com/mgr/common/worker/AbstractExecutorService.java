package com.mgr.common.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Sets.newLinkedHashSet;
import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Abstrakcyjna klasa wyzwalacza watkow
 *
 * @author michal
 */
public abstract class AbstractExecutorService {

    private final static Logger LOG = LoggerFactory
            .getLogger(AbstractExecutorService.class);

    protected WorkerFactory workerFactory;

    protected Set<Worker> workersPool;
    protected ExecutorService workersExecutorService;
    private Class<? extends Worker> workerType;

    public Class<? extends Worker> getWorkerType() {
        return workerType;
    }

    /**
     * Metoda inicjujaca, tworzymy watki oraz rejestrujemy je w puli
     *
     * @param workers
     */
    protected void init(Integer workers) {
        workersPool = newLinkedHashSet();
        workersExecutorService = newFixedThreadPool(workers);

        for (int i = 0; i < workers; i++) {
            workersPool.add(workerFactory.newWorker());
        }

        for (Worker worker : workersPool) {
            worker.start();
            workerType = worker.getClass();
            workersExecutorService.submit(worker);
            LOG.info("Worker created and started.");
        }
    }

    /**
     * Metoda wyzwalana przed zamknieciem obiektu
     */
    @PreDestroy
    public void shutdown() {

        workersExecutorService.shutdown();
        try {
            while (!workersExecutorService.awaitTermination(10,
                    TimeUnit.SECONDS)) {
                LOG.info("Awaiting completion of worker threads of type {}",
                        workerType);
                sendPoisonousRequests();
            }
        } catch (InterruptedException ie) {
            LOG.warn("Waiting for the worker threads to complete was interrupted.");
        }
        LOG.info("Worker pool closed.");
    }

    public Set<Worker> getProxyWorkers() {
        return workersPool;
    }

    protected abstract void sendPoisonousRequests();

}

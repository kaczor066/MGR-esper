package com.mgr.common.worker;

/**
 * Interfejs fabryki tworzacej watki
 *
 * @author michal
 */
public interface WorkerFactory {

    Worker newWorker();

    void close();

}

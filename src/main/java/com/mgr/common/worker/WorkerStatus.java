package com.mgr.common.worker;

/**
 * Wyliczenie stanow w jakich moze znajdowac sie watek
 *
 * @author michal
 */
public enum WorkerStatus {
    NEW, STARTED, STOPPING, STOPPED, WAITING_FOR_REBOOT
}

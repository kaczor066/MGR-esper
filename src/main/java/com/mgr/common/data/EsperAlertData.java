package com.mgr.common.data;

import java.math.BigDecimal;

/**
 * Interfejs opisujacy dowolna tabele z alertami
 *
 * @author michal
 */
public interface EsperAlertData {

    public Object getId();

    public BigDecimal getPriority();

    public void setPriority(BigDecimal priority);
}

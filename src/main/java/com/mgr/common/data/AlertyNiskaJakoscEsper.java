package com.mgr.common.data;

// Generated May 27, 2015 2:33:14 PM by Hibernate Tools 4.0.0

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Odpowiednik tabeli ALERTY_NISKA_JAKOSC_ESPER AlertyNiskaJakoscEsper generated
 * by hbm2java
 */
@Entity
@Table(name = "ALERTY_NISKA_JAKOSC_ESPER")
public class AlertyNiskaJakoscEsper implements java.io.Serializable,
        EsperAlertData {

    private AlertyNiskaJakoscEsperId id;
    private BigDecimal priority;
    private Timestamp updateDate;

    public AlertyNiskaJakoscEsper() {
    }

    public AlertyNiskaJakoscEsper(AlertyNiskaJakoscEsperId id,
                                  BigDecimal priority, Timestamp updateDate) {
        this.id = id;
        this.priority = priority;
        this.updateDate = updateDate;
    }

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "seria", column = @Column(name = "SERIA", nullable = false, length = 50)),
            @AttributeOverride(name = "poziom", column = @Column(name = "POZIOM", nullable = false, precision = 22, scale = 0))})
    public AlertyNiskaJakoscEsperId getId() {
        return this.id;
    }

    public void setId(AlertyNiskaJakoscEsperId id) {
        this.id = id;
    }

    @Column(name = "PRIORITY", nullable = false, precision = 22, scale = 0)
    public BigDecimal getPriority() {
        return this.priority;
    }

    public void setPriority(BigDecimal priority) {
        this.priority = priority;
    }

    @Column(name = "UPDATE_DATE", nullable = false)
    public Timestamp getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

}

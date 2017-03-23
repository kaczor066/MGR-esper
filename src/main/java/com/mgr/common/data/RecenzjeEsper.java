package com.mgr.common.data;

// Generated May 27, 2015 2:33:14 PM by Hibernate Tools 4.0.0

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Odpowiednik tabeli RECENZJE_ESPER RecenzjeEsper generated by hbm2java
 */
@Entity
@Table(name = "RECENZJE_ESPER", uniqueConstraints = @UniqueConstraint(columnNames = {
        "SERIA", "DOSTAWA_SKLEP", "DOSTAWA_KLIENT", "ID_OCENIAJACEGO"}))
public class RecenzjeEsper implements java.io.Serializable {

    private RecenzjeEsperId id;
    private BigDecimal zgodnosc;
    private BigDecimal czasDostawy;
    private BigDecimal jakosc;
    private BigDecimal pakowanie;
    private Timestamp dataRecenzji;
    private String idOcenianego;

    public RecenzjeEsper() {
    }

    public RecenzjeEsper(RecenzjeEsperId id) {
        this.id = id;
    }

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "seria", column = @Column(name = "SERIA", length = 50)),
            @AttributeOverride(name = "dostawaSklep", column = @Column(name = "DOSTAWA_SKLEP", length = 50)),
            @AttributeOverride(name = "dostawaKlient", column = @Column(name = "DOSTAWA_KLIENT", length = 50)),
            @AttributeOverride(name = "idOceniajacego", column = @Column(name = "ID_OCENIAJACEGO", nullable = false, length = 50)),})
    public RecenzjeEsperId getId() {
        return this.id;
    }

    public void setId(RecenzjeEsperId id) {
        this.id = id;
    }

    @Column(name = "ZGODNOSC", nullable = false, precision = 22, scale = 0)
    public BigDecimal getZgodnosc() {
        return this.zgodnosc;
    }

    public void setZgodnosc(BigDecimal zgodnosc) {
        this.zgodnosc = zgodnosc;
    }

    @Column(name = "CZAS_DOSTAWY", nullable = false, precision = 22, scale = 0)
    public BigDecimal getCzasDostawy() {
        return this.czasDostawy;
    }

    public void setCzasDostawy(BigDecimal czasDostawy) {
        this.czasDostawy = czasDostawy;
    }

    @Column(name = "JAKOSC", nullable = false, precision = 22, scale = 0)
    public BigDecimal getJakosc() {
        return this.jakosc;
    }

    public void setJakosc(BigDecimal jakosc) {
        this.jakosc = jakosc;
    }

    @Column(name = "PAKOWANIE", nullable = false, precision = 22, scale = 0)
    public BigDecimal getPakowanie() {
        return this.pakowanie;
    }

    public void setPakowanie(BigDecimal pakowanie) {
        this.pakowanie = pakowanie;
    }

    @Column(name = "DATA_RECENZJI", nullable = false)
    public Timestamp getDataRecenzji() {
        return this.dataRecenzji;
    }

    public void setDataRecenzji(Timestamp dataRecenzji) {
        this.dataRecenzji = dataRecenzji;
    }

    @Column(name = "ID_OCENIANEGO", nullable = false, length = 50)
    public String getIdOcenianego() {
        return this.idOcenianego;
    }

    public void setIdOcenianego(String idOcenianego) {
        this.idOcenianego = idOcenianego;
    }

}
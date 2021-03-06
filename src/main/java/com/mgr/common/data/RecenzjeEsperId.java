package com.mgr.common.data;

// Generated May 27, 2015 2:33:14 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Odpowiednik PK RECENZJE_ESPER_ID RecenzjeEsperId generated by hbm2java
 */
@Embeddable
public class RecenzjeEsperId implements java.io.Serializable {

    private String seria;
    private String dostawaSklep;
    private String dostawaKlient;
    private String idOceniajacego;

    public RecenzjeEsperId() {
    }

    public RecenzjeEsperId(String seria, String dostawaSklep,
                           String dostawaKlient, String idOceniajacego) {
        this.seria = seria;
        this.dostawaSklep = dostawaSklep;
        this.dostawaKlient = dostawaKlient;
        this.idOceniajacego = idOceniajacego;
    }

    @Column(name = "SERIA", length = 50, nullable = false)
    public String getSeria() {
        return this.seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    @Column(name = "DOSTAWA_SKLEP", length = 50, nullable = false)
    public String getDostawaSklep() {
        return this.dostawaSklep;
    }

    public void setDostawaSklep(String dostawaSklep) {
        this.dostawaSklep = dostawaSklep;
    }

    @Column(name = "DOSTAWA_KLIENT", length = 50, nullable = false)
    public String getDostawaKlient() {
        return this.dostawaKlient;
    }

    public void setDostawaKlient(String dostawaKlient) {
        this.dostawaKlient = dostawaKlient;
    }

    @Column(name = "ID_OCENIAJACEGO", nullable = false, length = 50)
    public String getIdOceniajacego() {
        return this.idOceniajacego;
    }

    public void setIdOceniajacego(String idOceniajacego) {
        this.idOceniajacego = idOceniajacego;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof RecenzjeEsperId))
            return false;
        RecenzjeEsperId castOther = (RecenzjeEsperId) other;

        return ((this.getSeria() == castOther.getSeria()) || (this.getSeria() != null
                && castOther.getSeria() != null && this.getSeria().equals(
                castOther.getSeria())))
                && ((this.getDostawaSklep() == castOther.getDostawaSklep()) || (this
                .getDostawaSklep() != null
                && castOther.getDostawaSklep() != null && this
                .getDostawaSklep().equals(castOther.getDostawaSklep())))
                && ((this.getDostawaKlient() == castOther.getDostawaKlient()) || (this
                .getDostawaKlient() != null
                && castOther.getDostawaKlient() != null && this
                .getDostawaKlient()
                .equals(castOther.getDostawaKlient())))

                && ((this.getIdOceniajacego() == castOther.getIdOceniajacego()) || (this
                .getIdOceniajacego() != null
                && castOther.getIdOceniajacego() != null && this
                .getIdOceniajacego().equals(
                        castOther.getIdOceniajacego())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getSeria() == null ? 0 : this.getSeria().hashCode());
        result = 37
                * result
                + (getDostawaSklep() == null ? 0 : this.getDostawaSklep()
                .hashCode());
        result = 37
                * result
                + (getDostawaKlient() == null ? 0 : this.getDostawaKlient()
                .hashCode());

        result = 37
                * result
                + (getIdOceniajacego() == null ? 0 : this.getIdOceniajacego()
                .hashCode());

        return result;
    }

}

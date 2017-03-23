package com.mgr.esper.events;

import java.sql.Timestamp;

/**
 * Klasa eventu recenzji, jest ona rejestrowana bezposrednio w silniku Esper
 *
 * @author michal
 */
public class ReviewEvent {
    public ReviewEvent(String seria, String dostawa_sklep,
                       String dostawa_klient, String id_oceniajacego,
                       String id_ocenianego, int zgodnosc, int czas_dostawy, int jakosc,
                       int pakowanie, Timestamp data_recenzji) {
        super();
        this.seria = seria;
        this.dostawa_sklep = dostawa_sklep;
        this.dostawa_klient = dostawa_klient;
        this.id_oceniajacego = id_oceniajacego;
        this.id_ocenianego = id_ocenianego;
        this.zgodnosc = zgodnosc;
        this.czas_dostawy = czas_dostawy;
        this.jakosc = jakosc;
        this.pakowanie = pakowanie;
        this.data_recenzji = data_recenzji;
    }

    private String seria;
    private String dostawa_sklep;
    private String dostawa_klient;
    private String id_oceniajacego;
    private String id_ocenianego;
    private int zgodnosc;
    private int czas_dostawy;
    private int jakosc;
    private int pakowanie;
    private Timestamp data_recenzji;

    public String getSeria() {
        return seria;
    }

    public String getDostawa_sklep() {
        return dostawa_sklep;
    }

    public String getDostawa_klient() {
        return dostawa_klient;
    }

    public String getId_oceniajacego() {
        return id_oceniajacego;
    }

    public String getId_ocenianego() {
        return id_ocenianego;
    }

    public int getZgodnosc() {
        return zgodnosc;
    }

    public int getCzas_dostawy() {
        return czas_dostawy;
    }

    public int getJakosc() {
        return jakosc;
    }

    public int getPakowanie() {
        return pakowanie;
    }

    public Timestamp getData_recenzji() {
        return data_recenzji;
    }

}

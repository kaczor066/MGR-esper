package com.mgr.esper.events;

/**
 * Klasa eventu ustawienia zakresow, jest ona rejestrowana bezposrednio w
 * silniku Esper
 *
 * @author michal
 */
public class SetRangeEvent {
    public SetRangeEvent(String seria, double zakres_dolny, double zakres_gorny) {
        super();
        this.seria = seria;
        this.zakres_dolny = zakres_dolny;
        this.zakres_gorny = zakres_gorny;
    }

    private String seria;
    private double zakres_dolny;
    private double zakres_gorny;

    public String getSeria() {
        return seria;
    }

    public double getZakres_dolny() {
        return zakres_dolny;
    }

    public double getZakres_gorny() {
        return zakres_gorny;
    }
}

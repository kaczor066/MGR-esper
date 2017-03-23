package com.mgr.esper.events;

/**
 * Klasa eventu pomiarow, jest ona rejestrowana bezposrednio w silniku Esper
 *
 * @author michal
 */
public class MeasureEvent {
    private String seria;
    private int pomiar_no;
    private double waga;

    public String getSeria() {
        return seria;
    }

    public int getPomiar_no() {
        return pomiar_no;
    }

    public double getWaga() {
        return waga;
    }

    public MeasureEvent(String seria, int pomiar_no, double waga) {
        super();
        this.seria = seria;
        this.pomiar_no = pomiar_no;
        this.waga = waga;
    }
}

package com.mgr.esper.events;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Klasa mapujaca obiekt zebrany z wewnetrznej kolejki do eventu Esper
 *
 * @author michal
 */
public class HashMapToEsperEventMapper {
    public Object map(HashMap<String, String> inputMap) {
        switch (inputMap.get("EVENT_NAME")) {
            case "EV_Pomiar":
                return mapMeasureEvent(inputMap);
            case "EV_Zakresy":
                return mapSetRangeEvent(inputMap);
            case "EV_Recenzja":
                return mapReviewEvent(inputMap);
            default:
                return null;
        }
    }

    /**
     * Metoda mapujaca HashMape na event Recenzji
     *
     * @param inputMap
     * @return
     */
    private Object mapReviewEvent(HashMap<String, String> inputMap) {
        return new ReviewEvent(inputMap.get("SERIA"),
                inputMap.get("DOSTAWA_SKLEP"), inputMap.get("DOSTAWA_KLIENT"),
                inputMap.get("ID_OCENIAJACEGO"), inputMap.get("ID_OCENIANEGO"),
                Integer.parseInt(inputMap.get("ZGODNOSC")),
                Integer.parseInt(inputMap.get("CZAS_DOSTAWY")),
                Integer.parseInt(inputMap.get("JAKOSC")),
                Integer.parseInt(inputMap.get("PAKOWANIE")),
                Timestamp.valueOf(inputMap.get("DATA_RECENZJI")));
    }

    /**
     * Metoda mapujaca HashMape na event Ustawienia Zakresow
     *
     * @param inputMap
     * @return
     */
    private Object mapSetRangeEvent(HashMap<String, String> inputMap) {
        return new SetRangeEvent(inputMap.get("SERIA"),
                Double.parseDouble(inputMap.get("ZAKRES_DOLNY")),
                Double.parseDouble(inputMap.get("ZAKRES_GORNY")));
    }

    /**
     * Metoda mapujaca HashMape na event Pomiarow
     *
     * @param inputMap
     * @return
     */
    private MeasureEvent mapMeasureEvent(HashMap<String, String> inputMap) {
        return new MeasureEvent(inputMap.get("SERIA"),
                Integer.parseInt(inputMap.get("POMIAR_NO")),
                Double.parseDouble(inputMap.get("WAGA")));
    }
}

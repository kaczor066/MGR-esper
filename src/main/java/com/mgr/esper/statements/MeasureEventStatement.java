package com.mgr.esper.statements;

import com.espertech.esper.client.EPAdministrator;

/**
 * Statement wrapper. To polecenie jest rejestrowane w silniku Esper. Odpowiada
 * za wysylanie wyliczonych zmiennych oraz zestawu danych potrzebnych
 * potrzebnych do wyliczenia wspolczynnika wydajnosci procesu.
 *
 * @author michal
 */
public class MeasureEventStatement extends EsperStatement {

    public MeasureEventStatement(EPAdministrator admin) {
        String stmt = "insert into MeasureWindow "
                + "select count(*) as cnt, seria, stddev(waga) as stddevWaga, avg(waga) as avgWaga "
                + "from MeasureEvent.win:length(10000) group by seria having count(*) > 1000";
        statement = admin.createEPL(stmt);

        stmt = "insert into CalculateCP "
                + " select measure.cnt, measure.stddevWaga, measure.avgWaga, measure.seria, range.zakres_dolny, range.zakres_gorny "
                + "from MeasureWindow.win:length(10000) as measure, SetRangeEvent.win:length(100) as range "
                + "where measure.seria = range.seria";

        statement = admin.createEPL(stmt);
    }

}

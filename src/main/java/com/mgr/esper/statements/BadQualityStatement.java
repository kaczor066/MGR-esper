package com.mgr.esper.statements;

import com.espertech.esper.client.EPAdministrator;

/**
 * Statement wrapper. To polecenie jest rejestrowane w silniku Esper. Odpowiada
 * za wykrywanie zlej jakosci.
 *
 * @author michal
 */
public class BadQualityStatement extends EsperStatement {

    public BadQualityStatement(EPAdministrator admin) {
        String stmt = "insert into BadQuality " + "select * "
                + "from ReviewEvent.win:length(1) where jakosc <= 3";
        statement = admin.createEPL(stmt);
    }
}
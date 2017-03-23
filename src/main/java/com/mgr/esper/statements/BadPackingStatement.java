package com.mgr.esper.statements;

import com.espertech.esper.client.EPAdministrator;

/**
 * Statement wrapper. To polecenie jest rejestrowane w silniku Esper. Odpowiada
 * za wykrywanie zlego pakowania.
 *
 * @author michal
 */
public class BadPackingStatement extends EsperStatement {

    public BadPackingStatement(EPAdministrator admin) {
        String stmt = "insert into BadPacking "
                + "select count(*) as cnt, avg(pakowanie) as avgPakowanie, id_ocenianego "
                + "from ReviewEvent.win:length(10000) group by id_ocenianego having count(*) > 5 and avg(pakowanie) < 3.5";
        statement = admin.createEPL(stmt);
    }
}

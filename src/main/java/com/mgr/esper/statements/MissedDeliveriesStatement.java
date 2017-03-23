package com.mgr.esper.statements;

import com.espertech.esper.client.EPAdministrator;

/**
 * Statement wrapper. To polecenie jest rejestrowane w silniku Esper. Odpowiada
 * za wykrywanie pomylonych dostaw.
 *
 * @author michal
 */
public class MissedDeliveriesStatement extends EsperStatement {

    public MissedDeliveriesStatement(EPAdministrator admin) {
        String stmt = "insert into LackOfCompatibility " + "select * "
                + "from ReviewEvent.win:length(1) where zgodnosc = 0";

        statement = admin.createEPL(stmt);

        stmt = "insert into MissedDeliveries "
                + "select count(*) as cnt, id_ocenianego "
                + "from LackOfCompatibility.win:time(48 hour) group by id_ocenianego"
                + " having count(*) > count(distinct id_oceniajacego) or count(*) = 1";

        statement = admin.createEPL(stmt);
    }
}

package com.mgr.esper.statements;

import com.espertech.esper.client.EPAdministrator;

/**
 * Statement wrapper. To polecenie jest rejestrowane w silniku Esper. Odpowiada
 * za wykrywanie zlych czasow dostawy.
 *
 * @author michal
 */
public class BadDeliveryTimeStatement extends EsperStatement {

    public BadDeliveryTimeStatement(EPAdministrator admin) {
        String stmt = "insert into BadDeliveryTime "
                + "select count(*) as cnt, avg(czas_dostawy) as avgCzasDostawy, id_ocenianego "
                + "from ReviewEvent.win:length(10000) group by id_ocenianego having count(*) > 5 and avg(czas_dostawy) < 3.5";
        statement = admin.createEPL(stmt);
    }
}

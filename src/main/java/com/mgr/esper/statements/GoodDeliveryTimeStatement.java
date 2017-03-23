package com.mgr.esper.statements;

import com.espertech.esper.client.EPAdministrator;

/**
 * Statement wrapper. To polecenie jest rejestrowane w silniku Esper. Odpowiada
 * za wykrywanie dobrych czasow dostawy.
 *
 * @author michal
 */
public class GoodDeliveryTimeStatement extends EsperStatement {

    public GoodDeliveryTimeStatement(EPAdministrator admin) {
        String stmt = "insert into GoodDeliveryTime "
                + "select count(*) as cnt, avg(czas_dostawy) as avgCzasDostawy, id_ocenianego "
                + "from ReviewEvent.win:length(10000) group by id_ocenianego having count(*) > 5 and avg(czas_dostawy) >= 3.5";

        statement = admin.createEPL(stmt);
    }
}

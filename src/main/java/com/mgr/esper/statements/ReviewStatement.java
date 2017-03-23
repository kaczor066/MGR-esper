package com.mgr.esper.statements;

import com.espertech.esper.client.EPAdministrator;

/**
 * Statement wrapper. To polecenie jest rejestrowane w silniku Esper. Odpowiada
 * za wyslanie recenzjo do okna Review.
 *
 * @author michal
 */
public class ReviewStatement extends EsperStatement {
    public ReviewStatement(EPAdministrator admin) {
        String stmt = "insert into Review " + "select *"
                + "from ReviewEvent.win:length(1)";
        statement = admin.createEPL(stmt);
    }
}

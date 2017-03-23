package com.mgr.esper.statements;

import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

/**
 * Abstrakcyjna klasa wrappera.
 *
 * @author michal
 */
public abstract class EsperStatement {

    protected EPStatement statement;

    public void addListener(UpdateListener listener) {
        statement.addListener(listener);
    }
}

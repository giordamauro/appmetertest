package org.unicen.ameter.core.support;

import org.unicen.ameter.core.model.Operation;

/**
 * Created by Mauro on 02/08/2016.
 */
public class EmptyOperation implements Operation<Void> {

    public static final EmptyOperation INSTANCE = new EmptyOperation();

    private EmptyOperation() {

    }

    @Override
    public Void execute() {
        return null;
    }
}

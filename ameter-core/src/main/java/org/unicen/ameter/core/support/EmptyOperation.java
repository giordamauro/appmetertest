package org.unicen.ameter.core.support;

import org.unicen.ameter.core.model.Operation;

/**
 * Created by Mauro on 02/08/2016.
 *
 * Singleton class. Represents a Void operation. Used to calculate framework's overhead time
 * and as a value for comparison with actual metrics.
 */
public class EmptyOperation implements Operation<Void> {

    public static final EmptyOperation INSTANCE = new EmptyOperation();

    @Override
    public Void execute() {
        return null;
    }
}

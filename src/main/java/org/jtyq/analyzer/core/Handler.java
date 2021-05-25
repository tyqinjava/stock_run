package org.jtyq.analyzer.core;

import java.util.Observable;

public class Handler extends Observable {

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }
}

package com;

import com.vaadin.navigator.View;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Label;

public class View3 extends Composite implements View {

    public View3() {
        setCompositionRoot(new CodeReport());
    }
}

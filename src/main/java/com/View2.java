package com;

import com.vaadin.navigator.View;
import com.vaadin.ui.Composite;

/**
 * @author Alejandro Duarte
 */
public class View2 extends Composite implements View {

    public View2() {
        setCompositionRoot(new TestCases());
    }
}
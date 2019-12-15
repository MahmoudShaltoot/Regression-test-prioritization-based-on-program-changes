package com;

import com.vaadin.navigator.View;
import com.vaadin.ui.Composite;

/**
 * @author Alejandro Duarte
 */
public class View1 extends Composite implements View {

    public View1() {
        setCompositionRoot(new upload());
    }
}

package org.fsolano.resources;

import org.fsolano.elements.UIActions;

public abstract class WebApp {
    protected UIActions uiActions;

    public WebApp(UIActions uiActions) {
        this.uiActions = uiActions;
    }
}

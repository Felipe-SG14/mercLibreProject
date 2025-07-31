package org.fsolano.pageObjects;

import org.fsolano.annotations.Element;
import org.fsolano.elements.UIActions;
import org.fsolano.elements.UIElement;
import org.fsolano.enums.SelectorType;
import org.fsolano.resources.WebApp;

public class MercadoLibreCommonPage extends WebApp {

    @Element(selectorType = SelectorType.ID, selector = "cb1-edit")
    private UIElement searchInput;

    @Element(selectorType = SelectorType.CLASS_NAME, selector = "nav-search-btn")
    private UIElement magnifyingGlassIcon;

    public MercadoLibreCommonPage(UIActions uiActions)
    {
        super(uiActions);
        UIElement.initElements(this);
    }

    public void search(String text)
    {
        uiActions.enterText(searchInput, text);
        uiActions.click(magnifyingGlassIcon);
    }
}

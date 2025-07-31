package org.fsolano.pageObjects;

import org.fsolano.annotations.Element;
import org.fsolano.elements.UIActions;
import org.fsolano.elements.UIElement;
import org.fsolano.enums.SelectorType;
import org.fsolano.resources.WebApp;

public class LandingPage extends WebApp {

    @Element(selectorType = SelectorType.ID, selector = "%s")
    private UIElement countryLabelDynamic;

    public LandingPage(UIActions uiActions)
    {
        super(uiActions);
        UIElement.initElements(this);
    }

    public void selectCountry(String country)
    {
        countryLabelDynamic.dynamicElement(country);
        uiActions.click(countryLabelDynamic);
    }
}

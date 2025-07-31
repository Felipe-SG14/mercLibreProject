package org.fsolano.resources;

import org.fsolano.elements.UIActions;
import org.fsolano.pageObjects.LandingPage;
import org.fsolano.pageObjects.MercadoLibreCommonPage;
import org.fsolano.pageObjects.MercadoLibreResultsPage;

public class WebPage {

    public LandingPage landingPage;
    public MercadoLibreCommonPage mercadoLibreCommonPage;
    public MercadoLibreResultsPage mercadoLibreResultsPage;

    public WebPage(UIActions uiActions)
    {
        landingPage = new LandingPage(uiActions);
        mercadoLibreCommonPage = new MercadoLibreCommonPage(uiActions);
        mercadoLibreResultsPage = new MercadoLibreResultsPage(uiActions);
    }

}

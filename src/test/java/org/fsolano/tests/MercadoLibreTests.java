package org.fsolano.tests;

import org.fsolano.driver.DriverManager;
import org.fsolano.elements.UIActions;
import org.fsolano.resources.Constants;
import org.fsolano.resources.TestBase;
import org.fsolano.resources.WebPage;
import org.testng.annotations.Test;

public class MercadoLibreTests extends TestBase {

    @Test
    public void takeHomeChallenge(){
        WebPage webPage = new WebPage(new UIActions(DriverManager.getDriver()));
        webPage.landingPage.selectCountry(Constants.COUNTRY_CODE_MX);
        webPage.mercadoLibreCommonPage.search(Constants.PRODUCT_PLAYSTATION5);
        webPage.mercadoLibreResultsPage.filterByCondition(Constants.CONDITION_NUEVO);
        webPage.mercadoLibreResultsPage.filterByOriginOfTheShipment(Constants.ORIGIN_OF_THE_SHIPMENT_LOCAL);
        webPage.mercadoLibreResultsPage.orderProductsBy(Constants.ORDER_PRODUCTS_BY_PRICE_DESC);
        webPage.mercadoLibreResultsPage.printFirstFiveProducts();
    }

}

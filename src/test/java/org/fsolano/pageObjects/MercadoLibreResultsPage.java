package org.fsolano.pageObjects;

import org.fsolano.annotations.Element;
import org.fsolano.elements.UIActions;
import org.fsolano.elements.UIElement;
import org.fsolano.enums.SelectorType;
import org.fsolano.resources.WebApp;
import org.fsolano.utilities.reports.LoggerManager;

import java.util.Comparator;
import java.util.List;

public class MercadoLibreResultsPage extends WebApp {

    @Element(selectorType = SelectorType.XPATH, selector = "//section[@class='ui-search-applied-filters']//div[@class='andes-tag__label']")
    private UIElement filters;

    @Element(selectorType = SelectorType.XPATH, selector = "//h3[text()='Condición']//following-sibling::ul//span[text()='%s']")
    private UIElement filterByConditionDynamic;

    @Element(selectorType = SelectorType.XPATH, selector = "//h3[text()='Origen del envío']//following-sibling::ul//span[text()='%s']")
    private UIElement filterByOriginOfTheShipmentDynamic;

    @Element(selectorType = SelectorType.XPATH, selector = "//div[@class='ui-search-sort-filter']//button//span")
    private UIElement orderByButton;

    @Element(selectorType = SelectorType.XPATH, selector = "//li[@data-key='%s']")
    private UIElement orderByButtonOptionDynamic;

    @Element(selectorType = SelectorType.XPATH, selector = "(//h3[@class='poly-component__title-wrapper']//a)[position() <= 5]")
    private UIElement firstFiveProductNames;

    @Element(selectorType = SelectorType.XPATH, selector = "(//div[@class='poly-card__content']//div[@class='poly-component__price' " +
            "and not(ancestor::div[@class='poly-component__buy-box'])]//div[@class='poly-price__current']" +
            "//span[@class='andes-money-amount__fraction'])[position() <= 5]")
    private UIElement firstFiveProductPrices;

    public MercadoLibreResultsPage(UIActions uiActions)
    {
        super(uiActions);
        UIElement.initElements(this);
    }

    public List<String> getActualFilters()
    {
        return uiActions.getElementsText(filters);
    }

    public void filterByCondition(String condition)
    {
        filterByConditionDynamic.dynamicElement(condition);
        uiActions.click(filterByConditionDynamic, true);
        List<String> actualFilters = getActualFilters();
        LoggerManager.assertTrue(actualFilters.contains(condition), condition + " filter was added");
    }

    public void filterByOriginOfTheShipment(String condition)
    {
        filterByOriginOfTheShipmentDynamic.dynamicElement(condition);
        uiActions.click(filterByOriginOfTheShipmentDynamic, true);
        List<String> actualFilters = getActualFilters();
        LoggerManager.assertTrue(actualFilters.contains(condition), condition + " filter was added");
    }

    public void orderProductsBy(String orderOption)
    {
        uiActions.click(orderByButton);
        orderByButtonOptionDynamic.dynamicElement(orderOption);
        uiActions.click(orderByButtonOptionDynamic);
    }

    public void printFirstFiveProducts() {
        uiActions.waitUntilIntegerElementsAreSorted(firstFiveProductPrices);
        List<String> productNames = uiActions.getElementsText(firstFiveProductNames);
        List<Integer> actualProductPrices = uiActions.getElementsText(firstFiveProductPrices)
                .stream().map(string -> Integer.parseInt(string.replace(",",""))).toList();
        List<Integer> sortedProductPrices = actualProductPrices.stream().sorted(Comparator.reverseOrder()).toList();
        LoggerManager.assertEquals(actualProductPrices, sortedProductPrices,
                "The products were sorted correctly - Expected: " + sortedProductPrices + " Actual: " + actualProductPrices);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < productNames.size(); i++) {
            sb.append("product: ").append(productNames.get(i)).append(" --- ")
                    .append(actualProductPrices.get(i)).append(System.lineSeparator());
        }
        System.out.println(sb);
        LoggerManager.pass(sb.toString());
    }
}

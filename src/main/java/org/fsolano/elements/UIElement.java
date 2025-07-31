package org.fsolano.elements;

import org.fsolano.annotations.Element;
import org.fsolano.enums.SelectorType;
import org.fsolano.enums.WaitType;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

public class UIElement {

    private By by;
    private SelectorType selectorType;
    private String selectorValue;
    private WaitType waitType;
    private int maxWaitTime;

    public By getBy() {
        return by;
    }

    public void setBy(By by) {
        this.by = by;
    }

    public String getSelectorValue() {
        return selectorValue;
    }

    public void setSelectorValue(String selectorValue) {
        this.selectorValue = selectorValue;
    }

    public SelectorType getSelectorType() {
        return selectorType;
    }

    public void setSelectorType(SelectorType selectorType) {
        this.selectorType = selectorType;
    }

    public WaitType getWaitType() {
        return waitType;
    }

    public void setWaitType(WaitType waitType) {
        this.waitType = waitType;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    private UIElement(Builder builder) {
        this.by = builder.by;
        this.selectorType = builder.selectorType;
        this.selectorValue = builder.selectorValue;
        this.waitType = builder.waitType;
        this.maxWaitTime = builder.maxWaitTime;
    }

    public static void initElements(Object pageObject)
    {
        Class<?> clazz = pageObject.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Element.class)) {
                Element annotation = field.getAnnotation(Element.class);
                if (annotation == null) {
                    throw new IllegalArgumentException("Element " + field.getName() + " does not have @Element annotation");
                }
                SelectorType selectorType = annotation.selectorType();
                String selectorValue = annotation.selector();
                By by = getBy(selectorType, selectorValue);
                WaitType waitType = annotation.waitType();
                int maxWaitTime = annotation.maxWaitTime();
                try {
                    field.setAccessible(true);
                    field.set(pageObject, new Builder()
                            .setBy(by)
                            .setSelectorType(selectorType)
                            .setSelectorValue(selectorValue)
                            .setWaitType(waitType)
                            .setMaxWaitTime(maxWaitTime)
                            .build());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void dynamicElement(Object... args) {
        this.selectorValue = String.format(this.selectorValue, args);
        this.by = getBy(this.selectorType, this.selectorValue);
    }

    private static By getBy(SelectorType selectorType, String selector) {
        return switch (selectorType) {
            case ID -> By.id(selector);
            case NAME -> By.name(selector);
            case CSS -> By.cssSelector(selector);
            case XPATH -> By.xpath(selector);
            case CLASS_NAME -> By.className(selector);
            case TAG_NAME -> By.tagName(selector);
            case LINK_TEXT -> By.linkText(selector);
            case PARTIAL_LINK_TEXT -> By.partialLinkText(selector);
        };
    }

    public static class Builder {
        private By by;
        private SelectorType selectorType;
        private String selectorValue;
        private WaitType waitType = WaitType.VISIBLE;
        private int maxWaitTime = 10;

        public Builder setBy(By by)
        {
            this.by = by;
            return this;
        }

        public Builder setSelectorType(SelectorType selectorType)
        {
            this.selectorType = selectorType;
            return this;
        }

        public Builder setSelectorValue(String selectorValue)
        {
            this.selectorValue = selectorValue;
            return this;
        }

        public Builder setWaitType(WaitType waitType) {
            this.waitType = waitType;
            return this;
        }

        public Builder setMaxWaitTime(int seconds) {
            this.maxWaitTime = seconds;
            return this;
        }

        public UIElement build() {
            if (by == null) {
                throw new IllegalStateException("Selector (By) should be defined");
            }
            return new UIElement(this);
        }
    }
}

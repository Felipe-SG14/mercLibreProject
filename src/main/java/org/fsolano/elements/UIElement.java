package org.fsolano.elements;

import org.fsolano.annotations.Locator;
import org.fsolano.enums.SelectorType;
import org.fsolano.enums.WaitType;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

public class UIElement {

    private By by;
    private WaitType waitType;
    private int maxWaitSeconds;

    public By getBy() {
        return by;
    }

    public void setBy(By by) {
        this.by = by;
    }

    public WaitType getWaitType() {
        return waitType;
    }

    public void setWaitType(WaitType waitType) {
        this.waitType = waitType;
    }

    public int getMaxWaitSeconds() {
        return maxWaitSeconds;
    }

    public void setMaxWaitSeconds(int maxWaitSeconds) {
        this.maxWaitSeconds = maxWaitSeconds;
    }

    private UIElement(Builder builder) {
        this.by = builder.by;
        this.waitType = builder.waitType;
        this.maxWaitSeconds = builder.maxWaitSeconds;
    }

    public static UIElement dynamicElement(Enum<?> enumValue, String... args) {
        try
        {
            Class<?> enumClass = enumValue.getDeclaringClass();
            Field field = enumClass.getField(enumValue.name());
            Locator locator = field.getAnnotation(Locator.class);
            if (locator == null) {
                throw new IllegalArgumentException("Enum " + enumValue + " does not have @Locator annotation");
            }
            SelectorType selectorType = locator.type();
            String selector = locator.value();
            for (String arg : args) {
                selector = selector.replaceFirst("\\?", arg);
            }
            By by = getBy(selectorType, selector);
            return new Builder().selector(by).build();
        }
        catch (NoSuchFieldException e)
        {
            throw new RuntimeException("Error creating dynamic element: ", e);
        }
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
        private WaitType waitType = WaitType.NONE;
        private int maxWaitSeconds = 10;

        public Builder selector(By by)
        {
            this.by = by;
            return this;
        }

        public Builder selector(Enum<?> enumValue) {
            try {
                Class<?> enumClass = enumValue.getDeclaringClass();
                Field field = enumClass.getField(enumValue.name());
                Locator locator = field.getAnnotation(Locator.class);
                if (locator == null) {
                    throw new IllegalArgumentException("Enum " + enumValue + " does not have @Locator annotation");
                }
                SelectorType selectorType = locator.type();
                String selector = locator.value();
                this.by = getBy(selectorType, selector);;

                return this;
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("Error creating by attribute: ", e);
            }
        }

        public Builder waitType(WaitType waitType) {
            this.waitType = waitType;
            return this;
        }

        public Builder maxWaitSeconds(int seconds) {
            this.maxWaitSeconds = seconds;
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

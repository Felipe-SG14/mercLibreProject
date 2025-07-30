package org.fsolano.annotations;

import org.fsolano.enums.SelectorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Locator {
    String value();
    SelectorType type() default SelectorType.ID;
}

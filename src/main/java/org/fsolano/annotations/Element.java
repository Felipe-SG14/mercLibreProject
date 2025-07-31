package org.fsolano.annotations;

import org.fsolano.enums.SelectorType;
import org.fsolano.enums.WaitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Element {
    String selector();
    SelectorType selectorType() default SelectorType.ID;
    WaitType waitType() default WaitType.VISIBLE;
    int maxWaitTime() default 10;
}

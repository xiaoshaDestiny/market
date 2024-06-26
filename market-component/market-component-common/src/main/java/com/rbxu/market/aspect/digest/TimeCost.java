package com.rbxu.market.aspect.digest;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeCost {

    String IDENTIFY_DEFAULT = "EMPTY";

    String businessIdentify() default IDENTIFY_DEFAULT;

}

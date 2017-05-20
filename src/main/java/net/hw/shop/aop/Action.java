package net.hw.shop.aop;

import java.lang.annotation.*;

/**
 * Created by howard on 2017/5/5.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}

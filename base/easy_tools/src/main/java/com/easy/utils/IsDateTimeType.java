package com.easy.utils;

import java.lang.annotation.*;

/**
 * @author zhouym
 * @version [1.0, 2018/3/19]
 */
@Documented
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsDateTimeType {
    boolean isDateTime() default false;
}

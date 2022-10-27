package com.luckyun.annotation;

import com.luckyun.DataMasking.DataMaskingFunc;

import java.lang.annotation.*;

/**
 * @program: desensitization
 * @description: 自定义数据注解，并可以配置数据脱敏策略
 * @author: yangwenjun
 * @create: 2022-10-27 09:56
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataMasking {
	DataMaskingFunc maskFunc() default DataMaskingFunc.NO_MASK;
}

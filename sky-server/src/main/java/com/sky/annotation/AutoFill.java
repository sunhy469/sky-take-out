package com.sky.annotation;


import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //指定注解使用的位置
@Retention(RetentionPolicy.RUNTIME) //指定何时可用
public @interface AutoFill {

    // 数据库操作类型 UPDATE INSERT
    OperationType value();

}

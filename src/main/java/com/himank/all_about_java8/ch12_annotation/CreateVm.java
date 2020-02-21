package com.himank.all_about_java8.ch12_annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(CreateVms.class)
public @interface CreateVm {
    String name();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CreateVms {
    CreateVm[] value();
}
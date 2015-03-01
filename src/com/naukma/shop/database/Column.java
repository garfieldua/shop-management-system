package com.naukma.shop.database;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	   String name() default "";
	   boolean ai() default false; // autoincrement
	   boolean primary() default false;
	   boolean required() default false;
}


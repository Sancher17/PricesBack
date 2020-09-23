package com.alexsoft.utils;


import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.entyties.categories.shops.BaseShop;

import java.lang.reflect.InvocationTargetException;


public class ContainerNew<T> {

    public static BaseShop createInstanceBaseShop(Class<? extends BaseShop> clazz) throws IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        return clazz.getDeclaredConstructor().newInstance();
    }

    public static BaseEntity createInstanceBaseEntity(Class<? extends BaseEntity> clazz) throws IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        return clazz.getDeclaredConstructor().newInstance();
    }
}

package com.alexsoft.utils;

import com.alexsoft.controllers.ShopRequest;
import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.entyties.categories.shops.BaseShop;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.nio.file.Paths;
import javax.lang.model.element.Modifier;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ClassCreator {

    public static void createShopClass(ShopRequest request) throws IOException {
//        MethodSpec main = MethodSpec.methodBuilder("main")
//                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                .returns(void.class)
//                .addParameter(String[].class, "args")
//                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
//                .build();
        createEntityClass(request);

        String categoryUpFirst = request.getCategory().substring(0, 1)
                .toUpperCase() + request.getCategory().substring(1);
        TypeSpec newShop = TypeSpec.classBuilder(categoryUpFirst + "Shop")
                .addModifiers(Modifier.PUBLIC)
                .superclass(BaseShop.class)
                .addAnnotation(Getter.class)
                .addAnnotation(Setter.class)
                .addAnnotation(NoArgsConstructor.class)
                .addAnnotation(Entity.class)
                .build();

        JavaFile javaFile = JavaFile.builder("com.prices.entyties.categories.shops", newShop)
                .build();

        javaFile.writeTo(Paths.get("src/main/java/"));
    }

    public static void createEntityClass(ShopRequest request) throws IOException {
        String categoryUpFirst = request.getCategory().substring(0, 1)
                .toUpperCase() + request.getCategory().substring(1);
        TypeSpec newShop = TypeSpec.classBuilder(categoryUpFirst)
                .addModifiers(Modifier.PUBLIC)
                .superclass(BaseEntity.class)
                .addAnnotation(Getter.class)
                .addAnnotation(Setter.class)
                .addAnnotation(NoArgsConstructor.class)
                .addAnnotation(Entity.class)
                .addAnnotation(AnnotationSpec.builder(JsonIgnoreProperties.class)
                        .addMember("value","$S","hibernateLazyInitializer")
                        .addMember("value","$S", "handler")
                        .build())
                .build();

        JavaFile javaFile = JavaFile.builder("com.prices.entyties.categories", newShop)
                .build();

        javaFile.writeTo(Paths.get("src/main/java/"));
    }
}

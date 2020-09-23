package com.alexsoft;

import org.springframework.stereotype.Component;

import com.alexsoft.entyties.categories.Notebook;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import lombok.SneakyThrows;

@Component
public class Test {



    @SneakyThrows
    public static void main(String[] args) {


        test();

    }




    private static void checkMapping (){

    }

    private static void test() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

////        String url = "https://catalog.onliner.by/sdapi/catalog.api/search/mobile";
//        String url = "https://catalog.onliner.by/sdapi/catalog.api/search/notebook";
//        ServiceOnliner service = new ServiceOnliner();
//        List<BaseShops> linksAndModels = service.getItemsFromSite(url, new NotebookShops(), 1);
//        System.out.println();

//        Notebook arg = (Notebook) Class.forName("notebook")
//                .getConstructor(String.class)
//                .newInstance("arg");

        String name = "notebook";
//        String name = "NOTEBOOK";
        String output = name.substring(0, 1).toUpperCase() + name.substring(1);;
        Notebook notebook  = (Notebook) Class.forName("com.prices.entyties.categories." + output).getConstructor().newInstance();
        System.out.println();

        Class<?> myClass = Class.forName("com.example.MyClass");
        Constructor<?> constructor = myClass.getConstructors()[0];
        constructor.newInstance();
        System.out.println();

    }
}

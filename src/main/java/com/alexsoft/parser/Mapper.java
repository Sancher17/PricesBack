package com.alexsoft.parser;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Mapper {

    public static Map<String, String> getMapping(String fileName){
        Yaml yaml = new Yaml();
        String baseFolder = "/mapping/";
        String typeOfFile = ".yml";
        String filePath = baseFolder + fileName + typeOfFile;
        InputStream inputStream = Mapper.class.getResourceAsStream(filePath);
        return yaml.load(inputStream);
    }
}

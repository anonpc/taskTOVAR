package org.example.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {

    public static Map<String, String> readConfig(String path) throws IOException {

        Map<String, String> map = new HashMap<>();
        FileReader fileReader = new FileReader(path);
        BufferedReader bf = new BufferedReader(fileReader);
        String line;
        while ((line = bf.readLine()) != null) {
            String[] keyValue = line.split("=");
            map.put(keyValue[0].replaceAll("\\s+", ""), keyValue[1].replaceAll("\\s+", ""));
        }
        return map;
    }
}

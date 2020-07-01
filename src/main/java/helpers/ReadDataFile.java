package helpers;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ReadDataFile {
    public static Properties properties;

    public static Properties getPro(String fileName) {
        String rootPath = System.getProperty("user.dir") + "/configs/";
        try {
            FileInputStream input = new FileInputStream(new File(rootPath + fileName));
            properties = new Properties();
            try {
                properties.load(new InputStreamReader(input, Charset.forName("UTF-8")));
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(fileName + " not found at " + rootPath);
        }
        return properties;
    }

}

package HelperClasses;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadFrom {

    public static String propertiesFile(String propertiesType, String data) throws NullPointerException{
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/test/resources/properties/" + propertiesType +".properties"));

        } catch (Exception e){
            System.out.println("Selected properties file was not found");
        }

        if (prop.getProperty(data) == null) {
            System.out.println("Null values from properties file");
            throw new NullPointerException();

        }
        return prop.getProperty(data);

    }

}

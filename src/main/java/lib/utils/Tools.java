package lib.utils;

import lib.exceptions.UnknownPropertyException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Tools {

    private static Properties properties = new Properties();

    public Tools () {
        properties = loadPropertiesFile();
    }

    private static final String PROPERTIES_FILE = "driver.properties";

    public String getPropertyOrException(String name) {
        return getMyProperty(name, true);
    }

    private String getPropertyFromPropertiesFile(String name) {
        Object result = properties.get(name);
        return result == null? null : result.toString();
    }

    private Properties loadPropertiesFile() {
        try {
            String filename = getPropertyOrNull(PROPERTIES_FILE);
            filename = filename == null? PROPERTIES_FILE :filename;

            InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
            stream = stream == null? new FileInputStream(new File(filename)) : stream;

            Properties result = new Properties();
            result.load(stream);
            return result;
        } catch (IOException e) {
            throw new UnknownPropertyException("Property file is not found");
        }
    }

    private String getPropertyOrNull(String name) {
        return getMyProperty(name, false);
    }

    private String getMyProperty(String name, boolean forceExceptionIfNotDefined) {
        String result;
        if ((result = System.getProperty(name, null)) != null && result.length() > 0) {
            return result;
        } else if ((result = getPropertyFromPropertiesFile(name)) != null && result.length() > 0) {
            return result;
        } else if (forceExceptionIfNotDefined) {
            throw new UnknownPropertyException("Unknown property: [" + name + "]");
        }
        return result;
    }
}
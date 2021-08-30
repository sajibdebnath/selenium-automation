package utils;

import java.util.ResourceBundle;

public class PropsUtils {
    private static ResourceBundle bundle = ResourceBundle.getBundle("config");

    public static String getString(String key) {
        return bundle.getString(key);
    }

    public static int getInteger(String key) {
        return Integer.parseInt(bundle.getString(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(bundle.getString(key));
    }
}

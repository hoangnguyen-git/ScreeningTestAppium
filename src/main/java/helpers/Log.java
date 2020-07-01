package helpers;

import org.apache.log4j.Logger;

public class Log {

    private static Logger Log = Logger.getLogger(helpers.Log.class.getName());

    public static void info(String message) {
        Log.info(message);
    }
}

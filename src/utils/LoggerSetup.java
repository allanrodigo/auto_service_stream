package utils;

import java.util.logging.*;

public class LoggerSetup {
    private static final Logger logger = Logger.getLogger(LoggerSetup.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("dealership_logs.txt", true);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void log(String message) {
        logger.info(message);
    }
}

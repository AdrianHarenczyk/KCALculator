package exceptions;

import utility.ConsoleColor;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ExceptionHandler {

    private static final String ERROR_FILE_LOGGER = "Could not load specified file.";

    private static Logger fileLogger = Logger.getGlobal();
    private static FileHandler fileHandler = initializeFileHandler();

    private static Logger consoleLogger = Logger.getGlobal();

    static {
        fileLogger.addHandler(fileHandler);
    }

    public static <T extends Exception> void handleException(T exception, String message) {
        consoleLogger.severe(ConsoleColor.MAGENTA + exception.getClass().getSimpleName()
                + " has occurred.\n" + message + "\n" + ConsoleColor.RESET);
        logStackTraceMessages(exception);
    }

    private static FileHandler initializeFileHandler() {
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("error_log_%g.dtd", 512, 3);
        } catch (IOException e) {
            fileHandler = null;
            fileLogger.severe(ERROR_FILE_LOGGER);
            fileLogger.severe(e.getCause().toString());
            System.exit(1);
        }
        return fileHandler;
    }

    private static <T extends Exception> void logStackTraceMessages(T exception) {
        StackTraceElement[] stackTraceMessages = exception.getStackTrace();
        for (StackTraceElement message : stackTraceMessages) {
            fileLogger.severe(message.toString());
        }
    }
}

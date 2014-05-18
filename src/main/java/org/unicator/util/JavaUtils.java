package org.unicator.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JavaUtils {

    public static String getCurrentTime() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public static void printToConsole(final String message) {
        System.out.println(getCurrentTime() + ": " + message);
    }

    public static void checkFileExists(final String fileName) {

        File projectFile = new File(fileName);
        if (!projectFile.exists()) {
            JavaUtils.printToConsole(String.format("File \"%s\" doesn\'t exists \n", fileName));
            System.exit(1);
        }

        if (!projectFile.isFile()) {
            JavaUtils.printToConsole(String.format("Entiny \"%s\" is not a file \n", fileName));
            System.exit(1);
        }

        if (!projectFile.canRead()) {
            JavaUtils.printToConsole(String.format("Could not read following file \"%s\" \n", fileName));
            System.exit(1);
        }
    }

    public static void checkDirectoryExists(final String directoryName) {

        File directoryFile = new File(directoryName);
        if (!directoryFile.exists()) {
            JavaUtils.printToConsole(String.format("Directory \"%s\" doesn\'t exists \n", directoryName));
            System.exit(1);
        }

        if (!directoryFile.isDirectory()) {
            JavaUtils.printToConsole(String.format("Entiny \"%s\" is not a directory \n", directoryName));
            System.exit(1);
        }

        if (!directoryFile.canRead()) {
            JavaUtils.printToConsole(String.format("Could not read following directory \"%s\" \n", directoryName));
            System.exit(1);
        }
    }

    public static String ensurePathHasSeparatorAtTheEnd(final String path) {
        String result = path;
        if (!StringUtils.isNullOrEmpty(result)) {
            if (!result.endsWith(File.separator)) {
                result += File.separator;
            }
        }
        return result;
    }

    public static boolean isFile(final String path) {
        final File pathFile = new File(path);
        return pathFile.isFile();
    }

}

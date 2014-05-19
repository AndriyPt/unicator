package org.unicator.application;

import java.io.IOException;

import org.unicator.util.IndexUtils;
import org.unicator.util.JavaUtils;

public class FilesIndexer {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Application usage: <program> <base path> \n");
            System.exit(1);
        }

        final String basePath = args[0];
        JavaUtils.checkDirectoryExists(basePath);

        try {
            IndexUtils.buildIndex(basePath);
        }
        catch (IOException e) {
            JavaUtils.printToConsole(e.getMessage());
        }
    }
}

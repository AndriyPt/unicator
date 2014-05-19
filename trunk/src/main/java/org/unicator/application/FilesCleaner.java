package org.unicator.application;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.unicator.comparator.IndexInfoComparator;
import org.unicator.model.IndexInfo;
import org.unicator.util.IndexUtils;
import org.unicator.util.JavaUtils;

public class FilesCleaner {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Application usage: <program> <base path> <duplicates path> "
                + "[<delete files (\"false\" by default)>] \n");
            System.exit(1);
        }

        final String basePath = args[0];
        JavaUtils.checkDirectoryExists(basePath);

        final String duplicatesPath = args[1];
        JavaUtils.checkDirectoryExists(duplicatesPath);

        boolean deleteFiles = false;
        if (args.length > 2) {
            deleteFiles = Boolean.parseBoolean(args[2]);
        }

        final File duplicatesDirectory = new File(duplicatesPath);
        final String duplicatesAbsolutePath = duplicatesDirectory.getAbsolutePath();

        try {
            final List<IndexInfo> indeces = IndexUtils.loadIndex(basePath);

            final Collection<File> allPossibleDuplicates = FileUtils.listFiles(duplicatesDirectory, null, true);

            for (File file : allPossibleDuplicates) {
                final IndexInfo currentFile = IndexUtils.getIndexInfoFromFile(duplicatesAbsolutePath, file);
                final int index = Collections.binarySearch(indeces, currentFile, new IndexInfoComparator());
                if (index >= 0) {
                    final File entryFromIndex = new File(basePath, indeces.get(index).getFilePath());
                    if (FileUtils.contentEquals(entryFromIndex, file)) {
                        JavaUtils.printToConsole("File " + file.getAbsolutePath() + " is duplicate of "
                            + entryFromIndex.getAbsolutePath());
                        if (deleteFiles) {
                            JavaUtils.printToConsole("Deleting file " + file.getAbsolutePath() + "...");
                            file.delete();
                        }
                    }

                }

            }

        }
        catch (IOException e) {
            JavaUtils.printToConsole(e.getMessage());
        }
    }

}

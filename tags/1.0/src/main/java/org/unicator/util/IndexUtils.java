package org.unicator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.unicator.comparator.IndexInfoComparator;
import org.unicator.model.IndexInfo;

public class IndexUtils {

    private static final String INDEX_FILE_NAME = "filesIndex.idx";
    
    public static IndexInfo getIndexInfoFromFile(final String absoluteBasePath, final File file) throws IOException {
        String path = file.getAbsolutePath().substring(absoluteBasePath.length());
        long fileSize = file.length();
        long checksum = FileUtils.checksumCRC32(file);
        return new IndexInfo(path, fileSize, checksum);
    }

    public static void buildIndex(final String basePath) throws IOException {

        final File directoryBasePath = new File(basePath);
        final String absoluteBasePath = directoryBasePath.getAbsolutePath();
        
        JavaUtils.printToConsole("Scanning folders...");

        final Collection<File> allFiles = FileUtils.listFiles(directoryBasePath, null, true);
        final List<IndexInfo> entries = new ArrayList<IndexInfo>(allFiles.size());
        
        JavaUtils.printToConsole("Building index...");
        int count = 1;
        for (File file : allFiles) {
            
            String statusMessage = "" + count + " of "  + allFiles.size();
            System.out.print(statusMessage);
            count++;
            
            entries.add(getIndexInfoFromFile(absoluteBasePath, file));
            
            JavaUtils.clearConsoleOutput(statusMessage);
        }
        
        JavaUtils.printToConsole("Soring index...");
        Collections.sort(entries, new IndexInfoComparator());

        JavaUtils.printToConsole("Saving index...");
        File indexFile = new File(basePath, INDEX_FILE_NAME);
        FileUtils.writeLines(indexFile, entries);
    }

    public static List<IndexInfo> loadIndex(final String basePath) throws IOException {

        final File indexFile = new File(basePath, INDEX_FILE_NAME);
        if (!indexFile.exists()) {
            JavaUtils.printToConsole("No index file found in " + basePath
                + "\n Please generate one using index creation util");
        }

        List<IndexInfo> result = new ArrayList<IndexInfo>();
        final BufferedReader br = new BufferedReader(new FileReader(indexFile));
        try {
            String line;
            while (null != (line = br.readLine())) {
                result.add(new IndexInfo(line));
            }
        }
        finally {
            br.close();
        }

        return result;
    }
}

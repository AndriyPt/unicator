package org.unicator.model;

import java.util.regex.Pattern;

public class IndexInfo {

    private static final String INDEX_SEPARATOR = "|";

    private String filePath;

    private long fileSize;

    private long fileChecksum;

    public IndexInfo(final String filePath, final long fileSize, final long fileChecksum) {
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileChecksum = fileChecksum;
    }

    public IndexInfo(final String serialized) {

        String[] elements = serialized.split(Pattern.quote(INDEX_SEPARATOR), 3);

        this.filePath = elements[0];
        this.fileSize = Long.parseLong(elements[1]);
        this.fileChecksum = Long.parseLong(elements[2]);
    }

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public long getFileChecksum() {
        return fileChecksum;
    }

    public final String toString() {
        return filePath + INDEX_SEPARATOR + fileSize + INDEX_SEPARATOR + fileChecksum;
    }

    public int compareTo(final IndexInfo object) {

        if (null == object) {
            return 1;
        }

        final Long thisFileSize = this.fileSize;
        int result = thisFileSize.compareTo(object.getFileSize());

        if (0 == result) {
            final Long thisChecksum = this.fileChecksum;
            result = thisChecksum.compareTo(object.getFileChecksum());
        }
        return result;
    }
}

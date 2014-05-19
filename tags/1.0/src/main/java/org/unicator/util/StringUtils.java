package org.unicator.util;

public class StringUtils {

    public static boolean isNullOrEmpty(final String value) {
        return (null == value) || value.isEmpty();
    }

    public static boolean isNullOrEmptyOrBlank(final String value) {

        if (null == value) {
            return true;
        }
        return isNullOrEmpty(value.trim());
    }
}
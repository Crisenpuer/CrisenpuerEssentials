package org.eu.pl.crisenpuer.spigot.crisenpuer_essentials.utils;

public class StringUtils {

    /**
     * Removes the specified prefix from the given string if it starts with that prefix.
     * @param str The string to process.
     * @param prefix The prefix to remove.
     * @return The string without the prefix, or the original string if it does not start with the prefix.
     */
    public static String removePrefix(String str, String prefix) {
        if (str != null && prefix != null && str.startsWith(prefix)) {
            return str.substring(prefix.length());
        }
        return str;
    }
}
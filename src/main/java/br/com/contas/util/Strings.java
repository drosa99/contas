package br.com.contas.util;

import org.apache.commons.lang3.StringUtils;

public class Strings {

    public static String formatSafe(String key, Object... args) {
        if (StringUtils.isBlank(key) || args == null || args.length == 0) {
            return key;
        }
        try {
            return String.format(key, args);
        } catch (Exception e) {
            return key;
        }
    }

    public static String getFirstLetter(String word) {
        if (StringUtils.isBlank(word)) {
            return null;
        }
        return word.substring(0, 1);
    }

    public static boolean hasEqualDigits(String text) {
        return text.matches("^" + text.charAt(0) + "+");
    }
}
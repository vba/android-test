package com.waracle.androidtest.tasks.tools;


public class CharsetUtil {
    private static final String CHARSET_KEY = "charset";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String CONTENT_TYPE_HIGHER_SPLITTER = ",";
    private static final String CONTENT_TYPE_LOWER_SPLITTER = "=";

    public static String extractCharset(String contentType) {
        if (contentType != null) {
            String[] params = contentType.split(CONTENT_TYPE_HIGHER_SPLITTER);
            for (int i = 1; i < params.length; i++) {
                String[] pair = params[i].trim().split(CONTENT_TYPE_LOWER_SPLITTER);
                if (pair.length == 2) {
                    if (pair[0].equals(CHARSET_KEY)) {
                        return pair[1];
                    }
                }
            }
        }
        return DEFAULT_ENCODING;
    }
}

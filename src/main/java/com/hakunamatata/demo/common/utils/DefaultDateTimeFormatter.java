package com.hakunamatata.demo.common.utils;

import java.time.format.DateTimeFormatter;

public class DefaultDateTimeFormatter {
    public static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    }

}

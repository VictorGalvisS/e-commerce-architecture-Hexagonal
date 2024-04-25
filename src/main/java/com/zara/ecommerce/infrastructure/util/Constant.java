package com.zara.ecommerce.infrastructure.util;

import java.time.format.DateTimeFormatter;

public class Constant {

    public static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(FORMAT_TIMESTAMP);
}

package com.yejianfengblue.spring.formatter;

import java.time.format.DateTimeFormatter;

/**
 * @author yejianfengblue
 */
public interface Formatter {

    String FLT_DATE_PATTERN = "ddMMMyy";
    /**
     * A {@link DateTimeFormatter} with pattern {@link #FLT_DATE_PATTERN}
     */
    DateTimeFormatter FLT_DATE_FORMATTER = DateTimeFormatter.ofPattern(FLT_DATE_PATTERN);
}

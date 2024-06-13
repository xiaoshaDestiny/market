package com.rbxu.market.constant;

import java.time.format.DateTimeFormatter;

public interface CommonConstant {

    String EMPTY_STR = "";

    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


}

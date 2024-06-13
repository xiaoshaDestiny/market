package com.rbxu.market.util;

import com.rbxu.market.constant.CommonConstant;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

    public static String formatOrElseEmpty(Long timeMillis, DateTimeFormatter formatter) {
        if (Objects.isNull(timeMillis)) {
            return CommonConstant.EMPTY_STR;
        }
        Date date = new Date(timeMillis);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return formatter.format(localDateTime);
    }
}

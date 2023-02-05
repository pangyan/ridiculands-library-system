package com.ridiculands.library.date;

import java.text.SimpleDateFormat;

public class DateUtil {

    public String formatDate(java.sql.Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}

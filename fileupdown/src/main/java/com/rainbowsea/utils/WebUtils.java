package com.rainbowsea.utils;

import java.time.LocalDateTime;

public class WebUtils {
    private WebUtils() {
    }

    public static String  getYearMonthDay() {
        // 如何得到当前的日期-》Java基础 日期，三代类
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        String yearMonthDay = year + "-" + monthValue + "-" + dayOfMonth;

        return yearMonthDay;
    }


    // 测试一下
    public static void main(String[] args) {
        System.out.println(WebUtils.getYearMonthDay());
    }
}

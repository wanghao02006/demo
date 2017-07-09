package com.example.demo.formatter;

import org.springframework.format.Formatter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by wh on 2017/7/3.
 */
public class CNLocalDateFormatter implements Formatter<LocalDate> {

    public static final String CN_PATTERN = "yyyyMMdd";

    public static final String NORMAL_PATTERN = "dd/MM/yyyy";

    @Override
    public LocalDate parse(String s, Locale locale) throws ParseException {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern(getCnPattern(locale)));
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return DateTimeFormatter.ofPattern(getCnPattern(locale)).format(localDate);
    }


    public static String getCnPattern(Locale locale){
        return isChina(locale) ? CN_PATTERN : NORMAL_PATTERN;
    }

    private static boolean isChina(Locale locale){
        return Locale.CHINA.getCountry().equals(locale.getCountry());
    }
}

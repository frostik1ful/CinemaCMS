package com.cinema.cinema.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

public class DateTimeParser {
    private static final SimpleDateFormat timeStempFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static String convertTimeStampToDateTimeLocal(Timestamp timestamp){
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
                .format(timestamp);
    }
    public static Date convertStringToDate(String string) throws ParseException {
        java.util.Date date = dateFormat.parse(string);
        return new Date(date.getTime());
    }
    public static Date[] convertDateRangeStringToDates(String dateRange) throws ParseException {
        Date[] mas = new Date[2];
        String[] strings = dateRange.split(" - ");
        /*java.util.Date startDate = dateFormat.parse(strings[0]);
        java.util.Date finishDate = dateFormat.parse(strings[1]);

        mas[0] = new Date(startDate.getTime());
        mas[1] = new Date(finishDate.getTime());*/
        mas[0] = convertStringToDate(strings[0]);
        mas[1] = convertStringToDate(strings[1]);
        return mas;

    }
    public static Date createDate(int year, int month, int day){
        return new Date(parseDateToMillis(year,month,day));
    }

    public static long parseDateToMillis (int year,int month,int day){
        GregorianCalendar calendar = new GregorianCalendar(year,--month,day);
        return calendar.getTimeInMillis();
    }
    public static LocalDateTime convertDateTimeStringToLocalDateTime(String time) throws ParseException {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(time,myFormatObj);

    }
}

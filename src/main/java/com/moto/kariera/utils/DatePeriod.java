package com.moto.kariera.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DatePeriod {
    List<LocalDate> localDates = new ArrayList<>();
    LocalDate firsSelectedDate;
    LocalDate secondSelectedDate;


    public String getDaysCountBetweenDates(LocalDate startDate, LocalDate endDate) {
        Long range = ChronoUnit.DAYS.between(startDate, endDate);
        String valueReturn = (range>=0) ? range.toString():"0";
        return "Days: " + valueReturn;
    }

    public String testSystem(Map<LocalDate, LocalDate> items) {
        for (Map.Entry<LocalDate, LocalDate> e : items.entrySet()) {
            localDates.add(e.getKey());
            localDates.add(e.getValue());
        }
        LocalDate a = localDates.get(0);
        LocalDate b = localDates.get(1);
        LocalDate c = localDates.get(2);
        LocalDate d = localDates.get(3);
        Integer var1 = a.compareTo(c);
        Integer var2 = b.compareTo(d);
        System.out.print(var1 + "  |  " + var2);
        firsSelectedDate = (var1 >= 0) ? a : c;
        secondSelectedDate = (var2 >= 0) ? d : b;
        return getDaysCountBetweenDates(firsSelectedDate, secondSelectedDate);
    }
}